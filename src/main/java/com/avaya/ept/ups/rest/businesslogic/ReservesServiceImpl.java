package com.avaya.ept.ups.rest.businesslogic;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaya.ept.ups.rest.domain.LPNState;
import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.domain.CacheTransferNumber;
import com.avaya.ept.ups.rest.exception.EndPointException;
import com.avaya.ept.ups.rest.util.BackendUtil;
import com.avaya.ept.ups.rest.util.RestConstants;
import com.gigaspaces.client.WriteModifiers;
import com.j_spaces.core.client.SQLQuery;

@Service("reserveService")
public class ReservesServiceImpl implements IReservesPortService {

	private static Logger logger = LoggerFactory.getLogger(ReservesServiceImpl.class);

	@Autowired
	private GigaSpace gigaSpace;
	
	@Autowired
	@Resource(name="restConfig")
	private Properties restConfig;

	@Override
	public CacheLandingPadNumber reserveLPN(String callId, String transferNumber,
			String version) throws EndPointException {
		logger.info("Begining reserveLPN");

		CacheLandingPadNumber cacheLpn = readOneAvailableLPNFromPool(callId, transferNumber, version);

		CacheLandingPadNumber lpn = new CacheLandingPadNumber();
		if (cacheLpn != null) {
			lpn.setPort(cacheLpn.getPort());
			lpn.setDnis(cacheLpn.getDnis());
		}
		lpn.setCallId(callId);
		lpn.setTransferNumber(transferNumber);
		lpn.setVersion(version);

		return lpn;
	}

	@Override
	public CacheLandingPadNumber retrieveLPN(String lpnNumber,
			String version) throws EndPointException {
		logger.info("Begining retrieveLPN");

		CacheLandingPadNumber lpn = new CacheLandingPadNumber();
		lpn.setPort(lpnNumber);
		lpn.setVersion(version);

		if (gigaSpace != null) {
			logger.info("retrieveLPN : gigaSpace query building");
			SQLQuery<CacheLandingPadNumber> query = new SQLQuery<CacheLandingPadNumber>(
					CacheLandingPadNumber.class, "port = ? AND lpnState = ?");
			query.setParameters(lpnNumber, LPNState.IN_USE);

			CacheLandingPadNumber[] result = gigaSpace.readMultiple(query);
			if (result != null && result.length > 0) {
				CacheLandingPadNumber cacheLPN = result[0];
				lpn.setCallId(cacheLPN.getCallId());
				lpn.setTransferNumber(cacheLPN.getTransferNumber());
				lpn.setDnis(cacheLPN.getDnis());
				lpn.setLpnState(cacheLPN.getLpnState());
				logger.info("gigaSpace.clear LPN obj = " + cacheLPN.toString());
				
				cacheLPN.setCallId(null);
				cacheLPN.setTransferNumber(null);
				cacheLPN.setVersion(null);
				cacheLPN.setExpTimeStamp(-1);
				cacheLPN.setLpnState(LPNState.AVAILABLE);
				
				updateObjectInMemory(cacheLPN);
			} else {
				logger.error(RestConstants.ERR_PORT_NOT_FOUND);
				throw new EndPointException(RestConstants.ERR_PORT_NOT_FOUND, RestConstants.ERRWS_CODE_2);
			}
		} else {
			logger.warn("gigaSpace obj null");
		}

		return lpn;
	}

	/**
	 * Read the available ports from database for the particular {@code callId}
	 * and {@code transferNumber}; and reserves the port. If there is no
	 * available ports then the return port will be null.
	 * 
	 * @param callId
	 *            value from request param
	 * @param transferNumber
	 *            value from request param
	 * @return port if port available then return string value, if not
	 *         {@code null} value will be returned
	 */
	private CacheLandingPadNumber readOneAvailableLPNFromPool(String callId,
			String transferNumber, String version) {
		CacheLandingPadNumber lpn = null;

		logger.info("readOneInavtiveLPNFromPool : gigaSpace query building");
		SQLQuery<CacheLandingPadNumber> query = new SQLQuery<CacheLandingPadNumber>(
				CacheLandingPadNumber.class, "lpnState = ?");
		query.setParameters(LPNState.AVAILABLE);

		CacheLandingPadNumber[] result = gigaSpace.readMultiple(query);
		if (result != null && result.length > 0) {
			lpn = result[0];
			
			lpn.setCallId(callId);
			lpn.setTransferNumber(transferNumber);
			lpn.setVersion(version);
			lpn.setExpTimeStamp(new Date().getTime() + (BackendUtil.getExpiredTimeInMilliSecFromConfig(restConfig)) );
			lpn.setLpnState(LPNState.IN_USE);

			updateObjectInMemory(lpn);
		} else {
			logger.error(RestConstants.ERR_OUT_OF_LPN_PORTS, new EndPointException(RestConstants.ERR_OUT_OF_LPN_PORTS));
		}

		return lpn;
	}

	/**
	 * update the object into memory
	 * 
	 * @param lpn
	 */
	private void updateObjectInMemory(CacheLandingPadNumber lpn) {
		logger.info("gigaSpace updateObjectInMemory = " + lpn.toString());
		
		if (gigaSpace != null) {
			gigaSpace.write(lpn, WriteModifiers.UPDATE_OR_WRITE);
		} else {
			logger.warn ("gigaSpace obj null");
		}
	}
	
	/**
	 * Read the configuration and returns the expired time value {@code cache.expireTime} in the properties file
	 * @return expiredTimeInSec
	 * 
	 */
	/*private int getExpiredTimeInSecFromConfig() {
		int expireTimeInSec = 2; // default
		if (restConfig != null) {
			String strExpireTime = (String) restConfig.get(RestConstants.EXP_INTERVAL_KEY);
			if (strExpireTime != null) {
				expireTimeInSec = Integer.parseInt(strExpireTime);
			}
		}
		return expireTimeInSec;
	}*/
	
	public boolean isValidTransferNumber(String transferNumber) throws EndPointException {
		boolean isValid = false;
		
		logger.info("isValidTransferNumber : gigaSpace query building");
		SQLQuery<CacheTransferNumber> query = new SQLQuery<CacheTransferNumber>(
				CacheTransferNumber.class, "transferNumber = ?");
		query.setParameters(transferNumber);
		
		CacheTransferNumber[] result = gigaSpace.readMultiple(query);
		if (result != null && result.length > 0) {
			isValid = true;
			CacheTransferNumber tfn = result[0];
			
			logger.debug("TransferNumber = " + tfn);			
		} else {
			logger.warn(RestConstants.ERR_INVALID_TFN_NUMBER, new EndPointException(RestConstants.ERR_INVALID_TFN_NUMBER));
		}
		
		return isValid;
	}

}
