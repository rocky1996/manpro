package com.cal.service;

import com.cal.entity.User;

public interface UserService {
	public void register(User u);//�û�ע��
	public User login(String account,String password);//�û���¼
	public User findaccount(String account);
}
