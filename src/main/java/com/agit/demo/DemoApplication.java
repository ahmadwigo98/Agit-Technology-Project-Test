package com.agit.demo;

import com.agit.demo.model.CustomerAccount;
import com.agit.demo.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			customerRepository.save(new CustomerAccount("111222333", "Ahmad", 10000));
			customerRepository.save(new CustomerAccount("444555666", "Wigo", 10000));
			customerRepository.save(new CustomerAccount("777888999", "Prasetya", 10000));
		};
	}

}
