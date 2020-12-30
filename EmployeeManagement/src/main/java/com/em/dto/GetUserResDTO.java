package com.em.dto;

import com.em.model.User;

public class GetUserResDTO {
    int responseStatus;
    String message;
    User user;

    public GetUserResDTO() {

    }

    public GetUserResDTO(int responseStatus, String message, User user) {
	this.responseStatus = responseStatus;
	this.message = message;
	this.user = user;
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

    /**
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }
    
}
