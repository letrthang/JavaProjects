package com.em.dao;

import org.springframework.stereotype.Repository;

import com.em.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

    /**
     * fetch all users with input conditions
     * 
     * @param minSalary
     * @param maxSalary
     * @param offset
     * @param limit
     * @return
     */
    List<User> findBySalary(Integer salary);

    /**
     * find user by login
     * 
     * @param minSalary
     * @param maxSalary
     * @param offset
     * @param limit
     * @return
     */

    List<User> findByLogin(String login);

    /**
     * find user by login
     * 
     * @param minSalary
     * @param maxSalary
     * @param offset
     * @param limit
     * @return
     */

    @Query("SELECT u FROM User u WHERE u.salary >= :minSalary and u.salary < :maxSalary")
    List<User> fechUsers(@Param("minSalary") Float minSalary, @Param("maxSalary") Float maxSalary);

}
