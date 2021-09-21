package com.mmk.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number")
public class Phone_Number {
	
	@Id
	private Integer id;
	private String number;
	private Integer account_id;
	/**
	 * @param id
	 * @param number
	 * @param account_id
	 */
	public Phone_Number(Integer id, String number, Integer account_id) {
		super();
		this.id = id;
		this.number = number;
		this.account_id = account_id;
	}
	/**
	 * 
	 */
	public Phone_Number() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	@Override
	public String toString() {
		return "phone_number [id=" + id + ", number=" + number + ", account_id=" + account_id + "]";
	}
	

}
