package com.lecompany.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lecompany.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
