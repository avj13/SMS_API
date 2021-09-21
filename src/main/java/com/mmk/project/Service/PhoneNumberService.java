package com.mmk.project.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmk.project.Repository.PhoneNumberRepository;
import com.mmk.project.model.Phone_Number;

@Service
public class PhoneNumberService {
	
	@Autowired
	private PhoneNumberRepository phonenumberRepository;
	
	public List<Phone_Number> getAllPhoneNumber()
	{
		List<Phone_Number> phone = new ArrayList<>();
		phonenumberRepository.findAll().
		forEach(phone::add);
		return phone;
	}
	
	public Optional<Phone_Number> getPhoneById(Integer id)
	{
		return phonenumberRepository.findById(id);
	}
	
	public void addPhone(Phone_Number phone)
	{
		phonenumberRepository.save(phone);
	}
	
	public void updatePhone(Phone_Number phone)
	{
		Phone_Number ph_upd = phonenumberRepository.getById(phone.getId());
		ph_upd.setNumber(phone.getNumber());
		ph_upd.setAccount_id(phone.getAccount_id());
		phonenumberRepository.saveAndFlush(ph_upd);
	}
	
	public void deletePhone(Integer id)
	{
		phonenumberRepository.deleteById(id);
	}

	public List<String> getPhoneByAccount_id(int curr_id) {
		List<Phone_Number> phone = new ArrayList<>();
		phone = phonenumberRepository.findByAccount_id(curr_id);
		
		List<String> num = new ArrayList<>();
		for (Phone_Number p : phone) {
			String s = p.getNumber();
			num.add(s);
		}
		
		return num;
	}
	
}
