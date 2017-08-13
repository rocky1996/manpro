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
			errors.put("account", "����Ϊ��!!!");
		}
		
		if(email==null || "".equals(email.trim().toString())){
			errors.put("email", "����Ϊ��!!!");
		}else{
			if(!email.matches("\\w+@\\w+(.\\w+)+")){
				errors.put("email", "��ʽ����ȷ����������!!!");
			}
		}
		
		if(nick==null || "".equals(nick.trim().toString())){
			errors.put("nick", "����Ϊ��!!!");
		}
		
		if(password==null || "".equals(password.trim().toString())){
			errors.put("password", "����Ϊ��!!!");
		}
		
		if(password==null || "".equals(password2.trim().toString())){
			errors.put("password2", "����Ϊ��!!!");
		}else{
			if(!password.equals(password2)){
				errors.put("password2", "�����������벻һ������������!!!");
			}
		}
		return errors.isEmpty();
	}
}
