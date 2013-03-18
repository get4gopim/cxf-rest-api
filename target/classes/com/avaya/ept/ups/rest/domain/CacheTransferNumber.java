package com.avaya.ept.ups.rest.domain;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;

@SpaceClass
public class CacheTransferNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3028739068014655905L;

	/*private String id;*/
	
	private String transferNumber;
	
	private String dnis;
	
	private Long transferNumberId;
	
	@SpaceId(autoGenerate=false)
	public Long getTransferNumberId() {
		return transferNumberId;
	}

	public void setTransferNumberId(Long transferNumberId) {
		this.transferNumberId = transferNumberId;
	}
	
	/*@SpaceId(autoGenerate=true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	@SpaceIndex
	public String getTransferNumber() {
		return transferNumber;
	}

	public void setTransferNumber(String transferNumber) {
		this.transferNumber = transferNumber;
	}

	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}
	
	@Override
	public String toString() {		
		StringBuilder builder = new StringBuilder();
		builder.append("TransferNumber [transferNumber=");
		builder.append(transferNumber);
		builder.append(", dnis=");
		builder.append(dnis);
		builder.append(", transferNumberId=");
		builder.append(transferNumberId);
		builder.append("]");
		return builder.toString();
	}
	
	

}
