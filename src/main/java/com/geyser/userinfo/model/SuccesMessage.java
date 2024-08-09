package com.geyser.userinfo.model;

import org.springframework.stereotype.Component;

@Component
public class SuccesMessage {

	private String successCode;
	private String successmessage;
	
	/**
	 * @param successCode
	 * @param successmessage
	 */
	public SuccesMessage() {
		
	}
	public SuccesMessage(String successCode, String successmessage) {
		super();
		this.successCode = successCode;
		this.successmessage = successmessage;
	}
	public String getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}
	public String getSuccessmessage() {
		return successmessage;
	}
	public void setSuccessmessage(String successmessage) {
		this.successmessage = successmessage;
	}
}
