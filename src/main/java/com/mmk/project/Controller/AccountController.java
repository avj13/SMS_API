package com.mmk.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmk.project.Service.AccountService;
import com.mmk.project.model.Account;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getall")
	public List<Account> getAllAccount()
	{
		return accountService.getAllAccount();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/account/{id}")
	public  Account getAccountById(@PathVariable Integer id) {
		return accountService.getAccountById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/account/add/{acc}")
	public void addAccount(@RequestBody Account acc) {
		accountService.addAccount(acc);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/account/upd/{acc}")
	public void updateAccount(@RequestBody Account acc) {
		accountService.updateAccount(acc);
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value = "account/del/{id}")
	public void deleteAccount(@PathVariable Integer id) {
		accountService.deleteaccount(id);
	}

}
