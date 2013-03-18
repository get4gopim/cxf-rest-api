package com.avaya.ept.ups.rest.exception;

import java.util.List;

import org.springframework.util.StringUtils;


public class EndPointException extends RuntimeException {
	
	
	private String errorCode;
	
	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7518806742397887403L;
	
	public EndPointException(String errorMsg, String errorCode) {
		super(errorMsg);
		this.errorCode = errorCode;
		
	}
	
	/**
	 * <p>Constructor for EndPointException.</p>
	 *
	 * @param bundle a {@link java.lang.String} object.
	 */
	public EndPointException(String bundle) {
		super(getErrorMessage(bundle,false,null));
	}

	/**
	 * <p>Constructor for EndPointException.</p>
	 *
	 * @param bundle a {@link java.lang.String} object.
	 * @param arguments a {@link java.util.List} object.
	 * @param areBundles a boolean.
	 */
	public EndPointException(String bundle,boolean areBundles, List<String> arguments) {
		super(getErrorMessage(bundle,areBundles,arguments));
	}
	
	/**
	 * @param bundle
	 * @return
	 */
	private static String getErrorMessage(String bundle,Boolean areBundles,List<String> arguments) {
		//Locale.setDefault(new Locale(ThreadLocalLocale.getLocale()));
		//StringBuffer bundleValue=new StringBuffer(getBundleFromRestMessages(bundle));
		StringBuffer bundleValue=new StringBuffer(bundle);
		if(arguments!=null) {
			StringBuffer params=new StringBuffer("");
			for(String argument:arguments) {
				if (StringUtils.hasText(params.toString())) {
					params.append(",");
				}
				if (areBundles) {
					//params.append(getBundleFromRestMessages(argument));
				}
				else {
					params.append(argument);
				}
			}
			bundleValue.append(" ( "+params+" )");
		}
		
		return bundleValue.toString();
	}
	
	/**
	 * <p>getBundleFromRestMessages.</p>
	 *
	 * @param bundle a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	/*public static String getBundleFromRestMessages(String bundle) {
		return getBundle(RestConstants.REST_VALIDATION_MESSAGES, bundle);
	}*/
	
	/**
	 * @param source
	 * @param bundle
	 * @return
	 */
	/*private static String getBundle(String source,String bundle) {
		logger.info("Getting Bundle ("+bundle+") with Locale ("+ThreadLocalLocale.getLocale()+")");
		platformResourceBundleLocator platformResourceBundleLocator = new PlatformResourceBundleLocator(source);
		ResourceBundle resourceBundle = platformResourceBundleLocator.getResourceBundle(new Locale(ThreadLocalLocale.getLocale()));
		return resourceBundle.getString(bundle);
	}*/

	
	
}
