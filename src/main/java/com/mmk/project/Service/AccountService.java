package com.mmk.project.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmk.project.Repository.AccountRepository;
import com.mmk.project.model.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository; 
	
	public List<Account> getAllAccount()
	{
		List<Account> acc = new ArrayList<>();
		accountRepository.findAll().
		forEach(acc::add);
		return acc;
	}
	
	public Account getAccountById(Integer id)
	{
		return accountRepository.getById(id);
	}
	
	public void addAccount(Account account)
	{
		accountRepository.save(account);
	}
	
	public void updateAccount(Account account)
	{
		Account acc_upd = accountRepository.getById(account.getId());
		acc_upd.setUsername(account.getUsername());
		acc_upd.setAuth_id(account.getAuth_id());
		//accountRepository.save(acc_upd);
		accountRepository.saveAndFlush(acc_upd);
	}
	
	public void deleteaccount(Integer id)
	{
		accountRepository.delete(getAccountById(id));
		//accountRepository.deleteById(id);
	}

}
