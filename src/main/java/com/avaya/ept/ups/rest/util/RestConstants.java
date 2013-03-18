package com.avaya.ept.ups.rest.util;

/**
 * <p>RestConstants class.</p>
 *
 * @author Gopinathan Mani
 * @version $Id: $Id
 */
public final class RestConstants {

	/**
	 * Path to restful webservices
	 */
	public static final String LPN_SERVICE_PATH				= "/landingpad";
	public static final String SAVE_PATH				= "/save";
	public static final String RETRIEVE_PATH			= "/retrieve";
	public static final String DUMMY_PATH				= "/dummy";
	
	/**
	 * restful webserice params
	 */
	public static final String CALL_ID				= "callid";
	public static final String TRANSFER_NUMBER		= "transfernumber";
	public static final String VERSION				= "version";
	public static final String PORT					= "port";
	
	/**
	 * restful response attribute values
	 */
	public static final String LPN_ROOT_ELEMENT		= "LandingPadNumber"; 
	public static final String LPN_PORT				= "port";
	public static final String DNIS				= "dnis";
	public static final String LPN_CALL_ID			= "callid";
	public static final String LPN_TRANSFER_NUMBER	= "transfernumber";
	
	public static final String DUMMY_RESPONSE_STRING	= "Rest Service is Alive !!";
	
	public static final String REST_VALIDATION_MESSAGES 		= "RestMessages";
	
	public static final String ERR_PORT_NOT_FOUND = "The parameters supplied are not matching any entries in gigaspaces cache";
	public static final String ERR_PARAM_NOT_EXIST = " does not exist in request param";
	public static final String ERR_OUT_OF_LPN_PORTS = "No ports available. Windward runs out of LPNs/Ports";
	public static final String ERR_INVALID_TFN_NUMBER = "Invalid transfer number";
	
	/*public static final String ERR_CALLID_NOT_EXIST = CALL_ID + " does not exist in request param";
	public static final String ERR_TRANSFER_NUMBER_NOT_EXIST = TRANSFER_NUMBER + " does not exist in request param";
	public static final String ERR_VERSION_NOT_EXIST = "version does not exist in request param";
	public static final String ERR_PORT_NOT_EXIST = "port does not exist in request param";*/
	
	public static final String ERRWS_CODE_1 = "ERRWS:01";
	public static final String ERRWS_CODE_2 = "ERRWS:02";
	public static final String ERRWS_CODE_3 = "ERRWS:03";
	
	
	public static final int INTERNAL_SERVER_ERROR	= 500;
	
	public static final int TOTAL_FEEDS	= 50;
	public static final String PORT_STARTS_WITH	= "720356";
	public static final String DNIS_STARTS_WITH	= "88800165047932";
	public static final String TFN_STARTS_WITH	= "888650";
	
	public static final String EXP_INTERVAL_KEY	= "cache.expireTime";
	
	
}
