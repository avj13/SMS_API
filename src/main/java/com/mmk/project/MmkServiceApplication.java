package com.mmk.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mmk.project.Repository.AccountRepository;
import com.mmk.project.Repository.PhoneNumberRepository;
import com.mmk.project.Repository.UserRepository;

@SpringBootApplication
public class MmkServiceApplication {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PhoneNumberRepository phoneNumberRepository;
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MmkServiceApplication.class, args);
	}

}
