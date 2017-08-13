package com.cal.service;

import com.cal.entity.User;

public interface UserService {
	public void register(User u);//用户注册
	public User login(String account,String password);//用户登录
	public User findaccount(String account);
}
