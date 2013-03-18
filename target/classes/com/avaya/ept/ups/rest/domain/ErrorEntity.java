package com.avaya.ept.ups.rest.domain;

import java.io.Serializable;

/**
 * <p>ErrorEntity class.</p>
 *
 * @author 
 * @version
 */
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ErrorEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5534874474912166190L;
	
	private String errorCode;
	private String briefError;
	private String detailedError;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * <p>Constructor for ErrorEntity.</p>
	 */
	public ErrorEntity() {
	}
	
	/**
	 * <p>Constructor for ErrorEntity.</p>
	 *
	 * @param briefError a {@link java.lang.String} object.
	 */
	public ErrorEntity(String briefError, String errorCode) {
		this.briefError = briefError;
		this.errorCode = errorCode;
	}
	
	/**
	 * <p>Constructor for ErrorEntity.</p>
	 *
	 * @param briefError a {@link java.lang.String} object.
	 * @param detailedError a {@link java.lang.String} object.
	 */
	public ErrorEntity(String briefError, String errorCode, String detailedError) {
		this.briefError = briefError;
		this.errorCode = errorCode;
		this.detailedError = detailedError;
	}

	/**
	 * <p>Getter for the field <code>briefError</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getBriefError() {
		return briefError;
	}

	/**
	 * <p>Getter for the field <code>detailedError</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDetailedError() {
		return detailedError;
	}

	/**
	 * <p>Setter for the field <code>briefError</code>.</p>
	 *
	 * @param briefError a {@link java.lang.String} object.
	 */
	public void setBriefError(String briefError) {
		this.briefError = briefError;
	}

	/**
	 * <p>Setter for the field <code>detailedError</code>.</p>
	 *
	 * @param detailedError a {@link java.lang.String} object.
	 */
	public void setDetailedError(String detailedError) {
		this.detailedError = detailedError;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "ErrorEntity [briefError=" + briefError + "]";
	}
}
