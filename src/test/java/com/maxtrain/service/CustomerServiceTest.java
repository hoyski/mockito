package com.maxtrain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.maxtrain.model.Customer;
import com.maxtrain.repository.CustomerRepository;

public class CustomerServiceTest {

    private CustomerRepository mockCustomerRepository;
    private Customer hoyski;
    private Optional<Customer> hoyskiFindResult;
    private Optional<Customer> emptyFindResult;
    private CustomerService customerService;

    @Before
    public void setup() {

	// Create a mock instance of a CustomerRepository. The mock exposes all of the
	// functionality of CustomerRepository without actually using any of its code
	mockCustomerRepository = Mockito.mock(CustomerRepository.class);

	// Create a populated Optional<Customer> to use for findById calls
	hoyski = new Customer();
	hoyski.setUserId("hoyski");
	hoyski.setFirstName("Dave");
	hoyski.setLastName("Hoy");
	hoyskiFindResult = Optional.of(hoyski);

	// Create an empty Optional<Customer> to use for findById calls
	emptyFindResult = Optional.empty();

	// Construct the CustomerService instance to use for the tests
	customerService = new CustomerService(mockCustomerRepository);
    }

    @Test
    public void isUserIdAvailable_returns_true_when_available() {

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(emptyFindResult);

	boolean hoyskiAvailable = customerService.isUserIdAvailable("hoyski");

	Assert.assertTrue("Expected hoyski to be available but wasn't", hoyskiAvailable);
    }

    @Test
    public void isUserIdAvailable_returns_false_when_in_use() {

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(hoyskiFindResult);

	boolean hoyskiAvailable = customerService.isUserIdAvailable("hoyski");

	Assert.assertFalse("Expected hoyski to be in use but wasn't", hoyskiAvailable);
    }

    @Test
    public void addNewCustomer_returns_customer_when_id_not_in_use() {

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(emptyFindResult);
	Mockito.when(mockCustomerRepository.save(Mockito.any(Customer.class))).thenReturn(hoyski);

	Customer savedCustomer = customerService.addNewCustomer(hoyski);

	Assert.assertNotNull("Expected Customer object but got null", savedCustomer);
    }

    @Test
    public void addNewCustomer_returns_null_when_id_in_use() {

	Mockito.when(mockCustomerRepository.findById("hoyski")).thenReturn(hoyskiFindResult);

	Customer savedCustomer = customerService.addNewCustomer(hoyski);

	Assert.assertNull("Expected null but got Customer object", savedCustomer);
    }

    @Test
    public void getExistingCustomer_returns_customer_when_exists() {
	Mockito.when(mockCustomerRepository.getOne("hoyski")).thenReturn(hoyski);

	Customer hoyskiFromDb = customerService.getExistingCustomer("hoyski");

	Assert.assertNotNull("Expected Customer object but got null", hoyskiFromDb);
    }

    @Test
    public void getExistingCustomer_returns_null_when_doesnt_exist() {
	Mockito.when(mockCustomerRepository.getOne("hoyski")).thenThrow(new EntityNotFoundException());

	Customer hoyskiFromDb = customerService.getExistingCustomer("hoyski");

	Assert.assertNull("Expected null but got Customer object", hoyskiFromDb);
    }

}
