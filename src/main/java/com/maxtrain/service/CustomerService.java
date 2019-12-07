package com.maxtrain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
     * Adds a new Customer to the database. Returns the newly added Customer or
     * <code>null</code> if the user ID is already in use.
     * 
     * @param newCustomer The customer to add
     */
    public Customer addNewCustomer(Customer newCustomer) {
	if (!isUserIdAvailable(newCustomer.getUserId())) {
	    return null;
	}

	return customerRepository.save(newCustomer);
    }

    /**
     * Returns the Customer with ID <code>userId</code> or <code>null</code> if the
     * Customer isn't found
     * 
     * @param userId
     * @return See above
     */
    public Customer getExistingCustomer(String userId) {
	try {
	    return customerRepository.getOne(userId);
	} catch (EntityNotFoundException ignored) {
	    return null;
	}
    }
}
