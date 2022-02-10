package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	List<Customer> getCustomers() {
		log.info("getCustomer was called");
		return customerRepository.findAll();
	}
	
	Customer getCustomerById(Long id) {
		return customerRepository
				.findById(id)
				.orElseThrow(() -> {
					NotFoundException notFoundException = new NotFoundException(
							"customer with id " + id + " not found");
					log.error("error in getting customer {}",id,notFoundException);
					return notFoundException;
				});
		
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
	public void updateCustomer(Customer customer) {
		Customer customerById = getCustomerById(customer.getId());
		customerById.setName(customer.getName());
		customerById.setEmail(customer.getEmail());
		customerById.setPassword(customer.getPassword());
		customerRepository.save(customerById);
	}
}
