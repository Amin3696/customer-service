package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CustomerServiceTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerService serviceUnderTest;
	
	@BeforeEach
	void setUp() {
		serviceUnderTest = new CustomerService(customerRepository);
	}
	
	@AfterEach
	void tearDown() {
		customerRepository.deleteAll();
	}
	
	@Test void getCustomers() {
		//given
		Customer customer1=new Customer(1L,"Amin","1234","amin@gmail.com");
		Customer customer2=new Customer(2L,"Shamissa","1234","Shamissa@gmial.com");
		
		customerRepository.saveAll(Arrays.asList(customer1,customer2));
		
		//when
		List<Customer> customers = serviceUnderTest.getCustomers();
		
		//then
		assertEquals(2,customers.size());
	}
	
	@Test void getCustomerById() {
		Customer customer1=new Customer(1L,"Amin","1234","amin@gmail.com");
		
		//given
		customerRepository.saveAll(Arrays.asList(customer1));
		
		//when
		Customer actualCustomer = serviceUnderTest.getCustomerById(1L);
		
		//then
		assertEquals(1L,actualCustomer.getId());
		assertEquals("Amin",actualCustomer.getName());
		assertEquals("1234",actualCustomer.getPassword());
		assertEquals("amin@gmail.com",actualCustomer.getEmail());
		
	}
	
	@Test void addCustomer() {
		Customer customer1=new Customer(1L,"Amin","1234","amin@gmail.com");
		
		//when
		serviceUnderTest.addCustomer(customer1);
		List<Customer> customers = serviceUnderTest.getCustomers();
		
		//then
		assertEquals(1,customers.size());
	}
	
	@Test void deleteCustomer() {
		Customer customer1=new Customer(1L,"Amin","1234","amin@gmail.com");
		
		//when
		serviceUnderTest.addCustomer(customer1);
		serviceUnderTest.deleteCustomer(1L);
		List<Customer> customers = serviceUnderTest.getCustomers();
		
		//then
		assertEquals(0,customers.size());
	}
	
	@Test void updateCustomer() {
		Customer customer1=new Customer(1L,"Amin","1234","amin@gmail.com");
		
		//when
		serviceUnderTest.addCustomer(customer1);
		serviceUnderTest.updateCustomer(new Customer(1L,"Ali","1234","ali@gmail.com"));
		Customer actualCustomer = serviceUnderTest.getCustomerById(1L);
		
		//then
		assertEquals(1L,actualCustomer.getId());
		assertEquals("Ali",actualCustomer.getName());
		assertEquals("ali@gmail.com",actualCustomer.getEmail());
		assertEquals("1234",actualCustomer.getPassword());
		
	
	}
}