package com.mmk.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.mmk.project.model.Account;

@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	//List<Account> getAllAccount();
	//Account getAccountById(Integer id);
	
	
	
}
