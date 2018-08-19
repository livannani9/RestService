package com.app.models;

public class IplResponce {
	
	private String errorCode;
	private String errorMessage;
	private String responceDate;
	
	
	
	@Override
	public String toString() {
		return "errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", responceDate="
				+ responceDate ;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getResponceDate() {
		return responceDate;
	}
	public void setResponceDate(String responceDate) {
		this.responceDate = responceDate;
	}
	
	
	

}
