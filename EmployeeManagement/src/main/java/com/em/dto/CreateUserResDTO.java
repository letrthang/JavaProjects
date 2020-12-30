package com.em.dto;

public class CreateUserResDTO {
    int responseStatus;
    String message;

    public CreateUserResDTO(int responseStatus, String message) {
	this.responseStatus = responseStatus;
	this.message = message;
    }

    /**
     * @return the responseStatus
     */
    public int getResponseStatus() {
	return responseStatus;
    }

    /**
     * @param responseStatus the responseStatus to set
     */
    public void setResponseStatus(int responseStatus) {
	this.responseStatus = responseStatus;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }
}
