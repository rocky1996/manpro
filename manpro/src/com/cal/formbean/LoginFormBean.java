package com.cal.formbean;

import java.util.HashMap;
import java.util.Map;

public class LoginFormBean {
	private String account;
	private String password;
	private Map<String, String> errors = new HashMap<String, String>();
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public boolean vaildate(){
		if(account==null || "".equals(account.trim().toString())){
			errors.put(account, "不能为空!!!");
		}
		if(password==null || "".equals(password.trim().toString())){
			errors.put(password, "不能为空!!!");
		}
		return errors.isEmpty();
	}
 } 
