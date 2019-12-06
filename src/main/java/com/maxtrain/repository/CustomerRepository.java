package com.maxtrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
