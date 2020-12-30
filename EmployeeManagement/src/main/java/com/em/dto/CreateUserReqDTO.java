package com.em.dto;

public class CreateUserReqDTO {
    String id;
    String login;
    String name;
    String salary;
    String startDate;

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
	return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
	this.login = login;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
	return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
	this.salary = salary;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
	return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String toJson() {

	return "{id:" + this.id + "," + "login:" + this.login + "," + "name:" + this.name + "," + "salary:"
		+ this.salary + "," + "startDate:" + this.startDate + "}";

    }

}
