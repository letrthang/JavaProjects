package com.em.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    String id;
    @Column(name = "login", unique = true, nullable = false)
    String login;
    @Column(name = "name")
    String name;
    @Column(name = "salary")
    Float salary;
    @Column(name = "start_date")
    Timestamp startDate;
    
    
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
    public Float getSalary() {
        return salary;
    }
    /**
     * @param salary the salary to set
     */
    public void setSalary(Float salary) {
        this.salary = salary;
    }
    /**
     * @return the startDate
     */
    public Timestamp getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

}
