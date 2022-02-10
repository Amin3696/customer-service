package com.example.demo.customer;

import java.util.Arrays;
import java.util.List;

public class CustomerFakeRepository implements CustomerRepo {
	
	@Override
	public List<Customer> getCustomers() {
		return Arrays.asList(
				new Customer(1L, "amin sh", "123", "email01"),
				new Customer(2L, "shamissa sh", "456", "email02"));
	}
}
