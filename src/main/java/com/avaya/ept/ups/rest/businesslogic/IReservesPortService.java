package com.avaya.ept.ups.rest.businesslogic;

import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.exception.EndPointException;

public interface IReservesPortService {

	/**
	 * Read the available ports from database for the particular {@code callId} 
	 * and {@code transferNumber}; and reserves the port.
	 * If there is no available ports then the landing pad number object will contains null port value.
	 * @param callId 
	 * 		value from request param
	 * @param transferNumber 
	 * 		value from request param
	 * @param version 
	 * 		value from request param
	 * @return landingPadNumber
	 * 		
	 */
	public CacheLandingPadNumber reserveLPN(String callId, String transferNumber, String version) throws EndPointException;
	
	/**
	 * Retrieve the LPN Object from the pool and return the LadingPadNumber along with  
	 * {@code callId} and {@code transferNumber};
	 * <p> 
	 * Once the record is read from the cache it will be evicted.
	 * </p>
	 * @param lpnNumber
	 * 		value from request param
	 * @param version
	 * 		value from request param
	 * @return landingPadNumber
	 * 		value from request param
	 */
	public CacheLandingPadNumber retrieveLPN(String lpnNumber, String version) throws EndPointException;
	
	/**
	 * Check the incoming {@code transferNumber} is valid or not. This will verify against 
	 * transfer number pool available in the memory.
	 *  
	 * @param transferNumber
	 * 		value from request param
	 * @return boolean
	 * 		true if the {@code transferNumber} is available in the cache
	 * 		else false
	 * @throws EndPointException
	 */
	public boolean isValidTransferNumber(String transferNumber) throws EndPointException;
}
