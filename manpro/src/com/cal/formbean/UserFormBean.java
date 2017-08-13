package com.cal.formbean;

import java.util.HashMap;
import java.util.Map;

public class UserFormBean {
	private String account;
	private String email;
	private String nick;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	private String password;
	private String password2;
	Map<String, String> errors = new HashMap<String, String>();
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public boolean vaildate(){
		if(account==null || "".equals(account.trim().toString())){
			errors.put("account", "不能为空!!!");
		}
		
		if(email==null || "".equals(email.trim().toString())){
			errors.put("email", "不能为空!!!");
		}else{
			if(!email.matches("\\w+@\\w+(.\\w+)+")){
				errors.put("email", "格式不正确，重新输入!!!");
			}
		}
		
		if(nick==null || "".equals(nick.trim().toString())){
			errors.put("nick", "不能为空!!!");
		}
		
		if(password==null || "".equals(password.trim().toString())){
			errors.put("password", "不能为空!!!");
		}
		
		if(password==null || "".equals(password2.trim().toString())){
			errors.put("password2", "不能为空!!!");
		}else{
			if(!password.equals(password2)){
				errors.put("password2", "两个密码输入不一样，重新输入!!!");
			}
		}
		return errors.isEmpty();
	}
}
