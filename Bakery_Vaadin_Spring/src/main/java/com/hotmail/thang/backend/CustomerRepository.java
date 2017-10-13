package com.hotmail.thang.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotmail.thang.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
