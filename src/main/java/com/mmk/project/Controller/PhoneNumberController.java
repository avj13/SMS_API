package com.mmk.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmk.project.Service.PhoneNumberService;
import com.mmk.project.model.Phone_Number;

@RestController
public class PhoneNumberController {
	
	@Autowired
	private PhoneNumberService phonenumberService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/phone/getall")
	public List<Phone_Number> getAllPhones(){
		return phonenumberService.getAllPhoneNumber();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/phone/{id}")
	public Optional<Phone_Number> getPhoneById(@PathVariable Integer id) {
		return phonenumberService.getPhoneById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/phone/add/{phn}")
	public void addPhone(@RequestBody Phone_Number phn) {
		phonenumberService.addPhone(phn);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/phone/upd/{phn}")
	public void updPhone(@RequestBody Phone_Number phn) {
		phonenumberService.updatePhone(phn);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/phone/del/{id}")
	public void delPhone(@PathVariable Integer id) {
		phonenumberService.deletePhone(id);
	}

}
