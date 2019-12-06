package com.maxtrain.service;

import java.util.Optional;

import com.maxtrain.model.Customer;
import com.maxtrain.repository.CustomerRepository;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
	this.customerRepository = customerRepository;
    }

    /**
     * Returns <code>true</code> if there are no Customers in the database with the
     * ID <code>userId</code>. Otherwise, return <code>false</code>
     * 
     * @param userId
     * @return See above
     */
    public boolean isUserIdAvailable(String userId) {
	Optional<Customer> existingCustomer = customerRepository.findById(userId);

	if (existingCustomer.isPresent()) {
	    // Customer with that ID already exists so ID isn't available
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * Adds a new Customer to the database.
     * 
     * @param newCustomer The customer to add @throws
     */
    public void addNewCustomer(Customer newCustomer) {
	customerRepository.save(newCustomer);
    }

    public Customer getExistingCustomer(String userId) {
	return customerRepository.getOne(userId);
    }
}
