package com.avaya.ept.ups.rest.service;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.util.RestConstants;

/**
 * 
 * @author gopinathan.m
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/cxf-context.xml", "/META-INF/gigaspaces/applicationContext.xml", "/META-INF/spring-security.xml", "/META-INF/app-context.xml"})
public class LandingPadServiceImplTest extends LandingPadServiceImpl {
	
	private static Logger logger = Logger.getLogger(LandingPadServiceImplTest.class);
	
	@Autowired
	private ILandingPadService landingPadService;
	
	
	@Before
	public void init() {
		logger.info("test init ");
	}

	@Test
	public void testWebServices() throws Exception {
		logger.info("testWebServices BEGIN");
		
		Assert.assertTrue(landingPadService != null);

		CacheLandingPadNumber tellMeLpn = landingPadService.reserveLPN(null, "7845265", "8886501", "1.0");
				
		Assert.assertTrue(tellMeLpn != null);
		
		if (tellMeLpn != null && tellMeLpn.getPort() != null) {
			CacheLandingPadNumber odAppLpn = landingPadService.retrieveLPN(null, tellMeLpn.getPort(), "1.0");
			
			Assert.assertTrue(tellMeLpn.getCallId() == odAppLpn.getCallId());
			Assert.assertTrue(tellMeLpn.getTransferNumber() == odAppLpn.getTransferNumber());
			Assert.assertTrue(tellMeLpn.getPort() == odAppLpn.getPort());
			Assert.assertTrue(tellMeLpn.getVersion() == odAppLpn.getVersion());
		} else {
			Assert.assertFalse("LPN Mismatch", false);
		}
		
		logger.info("testWebServices END");
	}
	
	@Test
	public void testInvalidTransferNumber() throws Exception {
		logger.info("testInvalidTransferNumber BEGIN");
		
		Assert.assertTrue(landingPadService != null);

		try {
			CacheLandingPadNumber lpn = landingPadService.reserveLPN(null, "00000", "1111111", "1.0");
			
			Assert.assertFalse(lpn != null);
		} catch (Exception ex) {
			logger.info("ex : " + ex.getMessage());
			boolean condition = RestConstants.ERR_INVALID_TFN_NUMBER.equalsIgnoreCase(ex.getMessage());
			Assert.assertTrue("Invalid Transfer Number Validation Success", condition);
		}
		
		
				
		logger.info("testInvalidTransferNumber END");
	}
	
	@Test
	public void testInvalidParameterTestForReserveLPN() throws Exception {
		logger.info("testInvalidParameterTestForReserveLPN BEGIN");
		
		Assert.assertTrue(landingPadService != null);

		try {
			CacheLandingPadNumber lpn = landingPadService.reserveLPN(null, null, "1111111", "1.0");
			
			Assert.assertFalse(lpn != null);
		} catch (Exception ex) {
			logger.info("ex : " + ex.getMessage());
			boolean condition = (RestConstants.CALL_ID + RestConstants.ERR_PARAM_NOT_EXIST).equalsIgnoreCase(ex.getMessage());
			Assert.assertTrue("Invalid Parameter Validation Success", condition);
		}
		
		
				
		logger.info("testInvalidParameterTestForReserveLPN END");
	}
	
	@Test
	public void testInvalidParameterTestForRetrieveLPN() throws Exception {
		logger.info("testInvalidParameterTestForRetrieveLPN BEGIN");
		
		Assert.assertTrue(landingPadService != null);

		try {
			CacheLandingPadNumber lpn = landingPadService.retrieveLPN(null, null, "1.0");
			
			Assert.assertFalse(lpn != null);
		} catch (Exception ex) {
			logger.info("ex : " + ex.getMessage());
			boolean condition = (RestConstants.PORT + RestConstants.ERR_PARAM_NOT_EXIST).equalsIgnoreCase(ex.getMessage());
			Assert.assertTrue("Invalid Parameter Validation Success", condition);
		}
		
		
				
		logger.info("testInvalidParameterTestForRetrieveLPN END");
	}
	
	@Test
	public void testRunOutOfLPN() throws Exception {
		logger.info("testRunOutOfLPN BEGIN");
		
		Assert.assertTrue(landingPadService != null);

		try {
			for (int i=0; i<RestConstants.TOTAL_FEEDS; i++) {
				landingPadService.reserveLPN(null, "7845265", RestConstants.TFN_STARTS_WITH + i, "1.0");
			}
			
			logger.info("testRunOutOfLPN : Out of Feeds Test");
			CacheLandingPadNumber lpn = landingPadService.reserveLPN(null, "7845265", "8886501", "1.0");
			
			Assert.assertTrue(lpn != null);
			Assert.assertTrue(lpn.getPort() == null);
			
		} catch (Exception ex) {
			logger.info("ex : " + ex.getMessage());
			Assert.assertFalse(false);
		}
		
		
				
		logger.info("testRunOutOfLPN END");
	}
	

}
