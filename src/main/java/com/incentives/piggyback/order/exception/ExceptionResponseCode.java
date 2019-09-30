package com.incentives.piggyback.order.exception;

public enum ExceptionResponseCode {

	GENRAL_ERROR(4006,"Your request could not be served by the system. Please try again."),
	UNAUTHORISED(4007, "Unauthorised access. Please retry with correct credentials."),
	MISSING_HEADER_KEY(4008,"Your request could not be served by the system. Please try again!"),
	TOKEN_REQUIRED(4009, "Token is missing or is incorrect"),
	USER_DATA_NOT_FOUND_IN_REQUEST(4012, "User data not found in request."),
	USER_DATA_NOT_FOUND_IN_RESPONSE(4013, "User data not found in response.");

	private int code;

	private String description;

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	private ExceptionResponseCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
}