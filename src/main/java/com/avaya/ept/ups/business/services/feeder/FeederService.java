package com.avaya.ept.ups.business.services.feeder;

import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.domain.CacheTransferNumber;
import com.avaya.ept.ups.rest.domain.LPNState;
import com.avaya.ept.ups.rest.util.RestConstants;
import com.gigaspaces.client.WriteModifiers;


public class FeederService implements InitializingBean {
	
	private static Logger logger = LoggerFactory.getLogger(FeederService.class);
	
	private static final int TOTAL_FEEDS = RestConstants.TOTAL_FEEDS;

	@Autowired
	private GigaSpace gigaSpace;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("Feeder start Init()");
		
		for (int i = 0; i < TOTAL_FEEDS; i++) {
			CacheLandingPadNumber lpn = new CacheLandingPadNumber();
			lpn.setLandingPadNumberId(new Long(i));
			lpn.setPort(RestConstants.PORT_STARTS_WITH + i);
			lpn.setLpnState(LPNState.AVAILABLE);
			lpn.setCallId(null);
			lpn.setTransferNumber(null);
			lpn.setVersion(null);
			lpn.setDnis(RestConstants.DNIS_STARTS_WITH + i);
			lpn.setExpTimeStamp(-1);
			
			writeObjectInMemory(lpn);
		}
		
		for (int i = 0; i < TOTAL_FEEDS; i++) {
			CacheTransferNumber tfn = new CacheTransferNumber();
			tfn.setTransferNumberId(new Long(i));
			tfn.setTransferNumber(RestConstants.TFN_STARTS_WITH + i);
			tfn.setDnis(RestConstants.DNIS_STARTS_WITH + i);
			
			writeTFNInMemory(tfn);
		}
		
		logger.debug("Feeder finish Init()");
	}
	
	private void writeObjectInMemory(CacheLandingPadNumber lpn) {
		logger.debug("Feeder writeObjectInMemory = " + lpn);
		if (gigaSpace != null) {
			gigaSpace.write(lpn, WriteModifiers.UPDATE_OR_WRITE); //(lpn, WriteModifiers.UPDATE_OR_WRITE);
		} else {
			logger.warn ("writeObject Failed: gigaSpace obj null");
		}
	}
	
	private void writeTFNInMemory(CacheTransferNumber tfn) {
		logger.debug("Feeder writeTFNInMemory = " + tfn);
		if (gigaSpace != null) {
			gigaSpace.write(tfn, WriteModifiers.UPDATE_OR_WRITE); //(lpn, WriteModifiers.UPDATE_OR_WRITE);
		} else {
			logger.warn ("writeObject Failed: gigaSpace obj null");
		}
	}

}
