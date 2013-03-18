package com.avaya.ept.ups.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.avaya.ept.ups.rest.util.RestConstants;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;



@XmlRootElement(name = RestConstants.LPN_ROOT_ELEMENT)
@SpaceClass
public class CacheLandingPadNumber implements Serializable {
	
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -4893344104269401673L;

	private String port;

	private String callId;

	private String transferNumber;
	
	private String version;
	
	private String dnis;
	
	/*@XmlTransient
	private String id;*/
	
	@XmlTransient
	private Long landingPadNumberId;
	
	@XmlTransient
	private LPNState lpnState;
	
	@XmlTransient
	private long expTimeStamp;
	
	@SpaceId(autoGenerate=false)
	@XmlTransient
	public Long getLandingPadNumberId() {
		return landingPadNumberId;
	}

	public void setLandingPadNumberId(Long landingPadNumberId) {
		this.landingPadNumberId = landingPadNumberId;
	}

	@XmlTransient
	public LPNState getLpnState() {
		return lpnState;
	}

	public void setLpnState(LPNState lpnState) {
		this.lpnState = lpnState;
	}

	@XmlTransient
	public long getExpTimeStamp() {
		return expTimeStamp;
	}

	public void setExpTimeStamp(long expTimeStamp) {
		this.expTimeStamp = expTimeStamp;
	}

	/*@SpaceId(autoGenerate=true)
	@XmlTransient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public CacheLandingPadNumber() { 
		
	}
	
	@XmlElement(name = RestConstants.DNIS)
	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	@XmlElement(name = RestConstants.LPN_PORT)
	@SpaceIndex
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@XmlElement(name = RestConstants.LPN_CALL_ID)
	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	@XmlElement(name = RestConstants.LPN_TRANSFER_NUMBER)
	public String getTransferNumber() {
		return transferNumber;
	}

	public void setTransferNumber(String transferNumber) {
		this.transferNumber = transferNumber;
	}

	@XmlElement(name = RestConstants.VERSION)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {		
		StringBuilder builder = new StringBuilder();
		builder.append("LandingPadNumber [port=");
		builder.append(port);
		builder.append(", callId=");
		builder.append(callId);
		builder.append(", transferNumber=");
		builder.append(transferNumber);
		builder.append(", landingPadNumberId=");
		builder.append(landingPadNumberId);
		builder.append(", version=");
		builder.append(version);
		builder.append(", expTimeStamp=");
		builder.append(expTimeStamp);
		builder.append(", lpnState=");
		builder.append(lpnState);
		builder.append(", dnis=");
		builder.append(dnis);
		builder.append("]");
		return builder.toString();
	}
	
	

}
