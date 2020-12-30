package com.em.dto;

import java.util.List;

import com.em.model.User;

public class FetchUserResDTO {
    int responseStatus;
    String message;
    List<User> results;

    public FetchUserResDTO(int responseStatus, String message, List<User> results) {
	this.responseStatus = responseStatus;
	this.message = message;
	this.results = results;
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
     * @return the results
     */
    public List<User> getResults() {
	return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<User> results) {
	this.results = results;
    }

}
