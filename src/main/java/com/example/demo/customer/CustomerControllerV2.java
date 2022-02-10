package com.example.demo.customer;

import com.example.demo.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/v2/customers") @RestController public class CustomerControllerV2 {
	
	private final CustomerService customerService;
	
	@GetMapping List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping(path = "{customerId}") Customer getCustomer(@PathVariable("customerId") Long id) {
		return customerService.getCustomerById(id);
	}
	
	@GetMapping(path = "{customerId}/exception") Customer getCustomerException(@PathVariable("customerId") Long id) {
		throw new ApiRequestException("ApiRequest for customer " + id);
	}
	
	@PostMapping void createNewCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("POST REQUEST...");
		customerService.addCustomer(customer);
	}
	
	@DeleteMapping(path = "{customerId}") void deleteCustomer(@PathVariable("customerId") Long id) {
		System.out.println("DELETE REQUEST FOR CUSTOMER WITH ID " + id);
		customerService.deleteCustomer(id);
	}
	
	@PutMapping void updateCustomer(@RequestBody Customer customer) {
		System.out.println("UPDATE REQUEST...");
		customerService.updateCustomer(customer);
	}
}
