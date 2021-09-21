package com.mmk.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	private Integer id;
	private String auth_id;
	private String username;
	/**
	 * @param id
	 * @param auth_id
	 * @param username
	 */
	public Account(Integer id, String auth_id, String username) {
		super();
		this.id = id;
		this.auth_id = auth_id;
		this.username = username;
	}
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "account [id=" + id + ", auth_id=" + auth_id + ", username=" + username + "]";
	}
	
}
