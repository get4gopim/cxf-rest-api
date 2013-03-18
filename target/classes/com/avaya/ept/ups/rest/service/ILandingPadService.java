package com.avaya.ept.ups.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.avaya.ept.ups.rest.domain.DummyResponse;
import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.util.RestConstants;


@Path(RestConstants.LPN_SERVICE_PATH)
public interface ILandingPadService {
	
	/**
	 * 
	 * @param uriInfo
	 * @param callId
	 * @param transferNumber
	 * @param version
	 * @return
	 */
	@GET
	@Path(RestConstants.SAVE_PATH)
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public CacheLandingPadNumber reserveLPN(@Context UriInfo uriInfo,
			@QueryParam(RestConstants.CALL_ID) String callId,
			@QueryParam(RestConstants.TRANSFER_NUMBER) String transferNumber,
			@QueryParam(RestConstants.VERSION) String version);
	
	/**
	 * 
	 * @param uriInfo
	 * @param port
	 * @param version
	 * @return
	 */
	@GET
	@Path(RestConstants.RETRIEVE_PATH)
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public CacheLandingPadNumber retrieveLPN(@Context UriInfo uriInfo,
			@QueryParam(RestConstants.PORT) String port,
			@QueryParam(RestConstants.VERSION) String version);
	
	
	/**
	 * 
	 * @param uriInfo
	 * @return
	 */
	@GET
	@Path(RestConstants.DUMMY_PATH)
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public DummyResponse getDummyResponse(@Context UriInfo uriInfo);

}
