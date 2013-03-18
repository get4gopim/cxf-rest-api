package com.avaya.ept.ups.rest.service;

import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avaya.ept.ups.rest.businesslogic.IReservesPortService;
import com.avaya.ept.ups.rest.domain.DummyResponse;
import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.exception.EndPointException;
import com.avaya.ept.ups.rest.util.RestConstants;

@Service("landingPadService")
public class LandingPadServiceImpl implements ILandingPadService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPadServiceImpl.class);
	
	@Autowired
	@Qualifier("reserveService")
	private IReservesPortService portService;

		
	public LandingPadServiceImpl() {
		
	}
	
	@Override
	public CacheLandingPadNumber reserveLPN(UriInfo uriInfo, String callId, String transferNumber, String version) {
		logger.info("Begining reserveLPN");
		if (uriInfo != null) {
			logger.debug(uriInfo.getAbsolutePath().toString());
		}
		
		if (callId == null) {
			throw new EndPointException(RestConstants.CALL_ID + RestConstants.ERR_PARAM_NOT_EXIST, RestConstants.ERRWS_CODE_1);
		}
		if (transferNumber == null) {
			throw new EndPointException(RestConstants.TRANSFER_NUMBER + RestConstants.ERR_PARAM_NOT_EXIST, RestConstants.ERRWS_CODE_1);
		}
		if (version == null) {
			throw new EndPointException(RestConstants.VERSION + RestConstants.ERR_PARAM_NOT_EXIST, RestConstants.ERRWS_CODE_1);
		}
		
		if (!portService.isValidTransferNumber(transferNumber)) {
			throw new EndPointException(RestConstants.ERR_INVALID_TFN_NUMBER, RestConstants.ERRWS_CODE_2);
		}
		
		
		try {
			CacheLandingPadNumber lpn = portService.reserveLPN(callId, transferNumber, version);
			return lpn;
		} catch (Exception ex) {
			throw new EndPointException(ex.toString());
		}
	}

	@Override
	public CacheLandingPadNumber retrieveLPN(UriInfo uriInfo, String port, String version) {
		logger.info("Begining retrieveLPN");
		if (uriInfo != null) {
			logger.debug(uriInfo.getAbsolutePath().toString());
		}
		
		if (port == null) {
			throw new EndPointException(RestConstants.PORT + RestConstants.ERR_PARAM_NOT_EXIST, RestConstants.ERRWS_CODE_1);
		}
		if (version == null) {
			throw new EndPointException(RestConstants.VERSION + RestConstants.ERR_PARAM_NOT_EXIST, RestConstants.ERRWS_CODE_1);
		}
		
		try {
			CacheLandingPadNumber lpn = portService.retrieveLPN(port, version);
			return lpn;
		} catch (Exception ex) {
			throw new EndPointException(ex.toString());
		}
	}

	@Override
	public DummyResponse getDummyResponse(UriInfo uriInfo) {
		logger.info("Begining getDummyResponse");
		if (uriInfo != null) {
			logger.debug(uriInfo.getAbsolutePath().toString());
		}
		
		return new DummyResponse(RestConstants.DUMMY_RESPONSE_STRING);
	}
}
