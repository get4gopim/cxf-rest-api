package com.avaya.ept.ups.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DummyResponse implements Serializable {

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -7967246517714966337L;
	
	private String message;
	
	public DummyResponse() {}
	
	public DummyResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
