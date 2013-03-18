package com.avaya.ept.ups.rest.util;

import java.util.Properties;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaya.ept.ups.rest.domain.ErrorEntity;
import com.avaya.ept.ups.rest.exception.EndPointException;

public final class BackendUtil {

	private static Logger logger = LoggerFactory.getLogger(BackendUtil.class);
	
	/**
	 * Read the configuration and returns the expired time value {@code cache.expireTime} in the properties file
	 * @return expiredTimeInSec
	 * 
	 */
	public static int getExpiredTimeInSecFromConfig(Properties restConfig) {
		int expireTimeInSec = 2; // default
		if (restConfig != null) {
			String strExpireTime = (String) restConfig.get(RestConstants.EXP_INTERVAL_KEY);
			if (strExpireTime != null) {
				expireTimeInSec = Integer.parseInt(strExpireTime);
			}
		}
		return expireTimeInSec * 60;
	}
	
	/**
	 * Read the configuration and returns the expired time value {@code cache.expireTime} in the properties file
	 * @return expiredTimeInSec
	 * 
	 */
	public static int getExpiredTimeInMilliSecFromConfig(Properties restConfig) {
		return getExpiredTimeInSecFromConfig(restConfig) * 1000;
	}
	

	/**
	 * <p>createWebException.</p>
	 *
	 * @param e a {@link java.lang.Exception} object.
	 * @return a {@link javax.ws.rs.WebApplicationException} object.
	 */
	public static WebApplicationException createWebException(Exception e) {
		return createWebException(RestConstants.INTERNAL_SERVER_ERROR, Family.SERVER_ERROR, e);
	}
	
	public static Response createWebExceptionResponse(String statusCode, Family family, EndPointException e) {
		logger.error("Rest service error", e);
		String message=e.getMessage();
		
		if(message==null) {
			message="Exception has no message";
		}
		StringBuffer errorMessage=new StringBuffer(message);
		
		logger.info("Message:"+errorMessage);

		ErrorEntity errorEntity = new ErrorEntity(errorMessage.toString(), statusCode);
		ServerStatusType statusType = new ServerStatusType(RestConstants.INTERNAL_SERVER_ERROR, family, errorMessage.toString());
		return Response.status(statusType).entity(errorEntity).build();
	}
	
	/**
	 * <p>createWebException.</p>
	 *
	 * @param statusCode a int.
	 * @param family a {@link javax.ws.rs.core.Response.Status.Family} object.
	 * @param e a {@link java.lang.Exception} object.
	 * @return a {@link javax.ws.rs.WebApplicationException} object.
	 */
	public static WebApplicationException createWebException(int statusCode, Family family, Exception e) {
		logger.error("Rest service error", e);
		String message=e.getMessage();
		
		if(message==null) {
			message="Exception has no message";
		}
		StringBuffer errorMessage=new StringBuffer(message);
		
		logger.info("Message:"+errorMessage);

		ErrorEntity errorEntity=new ErrorEntity(errorMessage.toString(), String.valueOf(statusCode));
		ServerStatusType statusType = new ServerStatusType(statusCode, family, errorMessage.toString());
		return new WebApplicationException(Response.status(statusType).entity(errorEntity).build());
	}
	
	static class ServerStatusType implements Response.StatusType {

		private int statusCode;
		private Family family;
		private String reasonPhrase;

		public ServerStatusType(int statusCode, Family family, String reasonPhrase) {
			this.statusCode = statusCode;
			this.family = family;
			this.reasonPhrase = reasonPhrase;
		}

		@Override
		public int getStatusCode() {
			// TODO Auto-generated method stub
			return statusCode;
		}

		@Override
		public Family getFamily() {
			// TODO Auto-generated method stub
			return family;
		}

		@Override
		public String getReasonPhrase() {
			// TODO Auto-generated method stub
			return reasonPhrase;
		}

	}
}
