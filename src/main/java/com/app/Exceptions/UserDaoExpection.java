package com.app.Exceptions;

public class UserDaoExpection extends RuntimeException {
	
	private static final long serialVersionUID = -6439438176277114685L;
	private int reasonCode;
	private String reason;
	private String message;
	
	public UserDaoExpection(int reasonCode, String reason, String message) {
		super(message);
		this.reasonCode = reasonCode;
		this.reason = reason;
		this.message = message;
	}


	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "reasonCode=" + reasonCode + ", reason=" + reason + ", message=" + message;
	}

}
