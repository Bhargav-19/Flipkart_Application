package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "register")
public class Register {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
	private String userName;
	private String email;
	private String mobile;
	private String password;
	
	

	
	
	public Register(Long Id, String userName, String email, String mobile, String password) {
		super();
		this.Id = Id;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}

	 public Register() {
	    }
	
	
	
    
	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Register [Id=" + Id + ", userName=" + userName + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + "]";
	}
	
	
	
	
}
