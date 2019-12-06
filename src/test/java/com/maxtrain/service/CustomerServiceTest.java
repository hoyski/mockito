package com.maxtrain.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.maxtrain.model.Customer;
import com.maxtrain.repository.CustomerRepository;

public class CustomerServiceTest {

    private CustomerRepository mockCustomerRepository;
    private CustomerService customerService;

    @Before
    public void setup() {

	// Create a mock instance of a CustomerRepository. The mock exposes all of the
	// functionality of CustomerRepository without actually using any of its code
	mockCustomerRepository = Mockito.mock(CustomerRepository.class);

	// Construct the CustomerService instance to use for the tests
	customerService = new CustomerService(mockCustomerRepository);
    }

    @Test
    public void isUserIdAvailable_returns_true_when_available() {
	Optional<Customer> emptyFindResult = Optional.empty();

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(emptyFindResult);

	boolean hoyskiAvailable = customerService.isUserIdAvailable("hoyski");

	Assert.assertTrue("Expected hoyski to be available but wasn't", hoyskiAvailable);
    }

    @Test
    public void isUserIdAvailable_returns_false_when_in_use() {
	Optional<Customer> hoyskiFindResult = Optional.of(new Customer());

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(hoyskiFindResult);

	boolean hoyskiAvailable = customerService.isUserIdAvailable("hoyski");

	Assert.assertFalse("Expected hoyski to be in use but wasn't", hoyskiAvailable);
    }
}
