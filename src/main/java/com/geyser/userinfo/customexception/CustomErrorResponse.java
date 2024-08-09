package com.geyser.userinfo.customexception;

public class CustomErrorResponse {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	public CustomErrorResponse() {
		
	}
	public CustomErrorResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
