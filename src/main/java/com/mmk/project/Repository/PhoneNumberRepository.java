package com.mmk.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mmk.project.model.Phone_Number;

@EnableJpaRepositories
public interface PhoneNumberRepository extends JpaRepository<Phone_Number, Integer>{

	List<Phone_Number> findByAccount_id(Integer account_id);

	List<Phone_Number> findByAccount_id(int curr_id); 

//	Phone_Number getById(int id);
	
//	List<Phone_Number> getAllPhoneNumber();
//	Optional<Phone_Number> getPhoneById(Integer id);
		

}
