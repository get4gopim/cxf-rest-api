package com.avaya.ept.ups.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avaya.ept.ups.rest.domain.ErrorEntity;
import com.avaya.ept.ups.rest.util.RestConstants;

public class EndPointExceptionMapper implements ExceptionMapper<EndPointException>
{
	
	  private static Logger logger = LoggerFactory.getLogger(EndPointExceptionMapper.class);
	
	  public Response toResponse(EndPointException e)
	  {
		  logger.error(e.getMessage());
		  
		  ErrorEntity errorEntity = new ErrorEntity(e.getMessage(), e.getErrorCode());
		  ServerStatusType statusType = new ServerStatusType(RestConstants.INTERNAL_SERVER_ERROR, Family.SERVER_ERROR, e.getMessage());
		  return Response.status(statusType).entity(errorEntity).build();
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


