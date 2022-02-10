package com.example.demo.customer;

import com.example.demo.infoapp.InfoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration public class CustomerConfiguration {
	
	@Value("${app.useFakeCustomerRepo:false}") private Boolean useFakeCustomerRepo;
	
	@Autowired
	private Environment environment;
	
	@Bean CommandLineRunner commandLineRunner(InfoApp infoApp) {
		return args -> {
			System.out.println("Command line runner hooray");
			System.out.println("App name: "+infoApp.getName());
			System.out.println("Description: "+infoApp.getDescription());
			System.out.println("Version: "+infoApp.getVersion());
			System.out.println("Company: "+environment.getProperty("info.company.name"));
		};
	}
	
	@Bean CustomerRepo customerRepo() {
		System.out.println("useFakeCustomerRepo = " + useFakeCustomerRepo);
		return new CustomerFakeRepository();
		
	}
}
