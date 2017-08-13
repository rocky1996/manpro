package com.cal.mapper;

import com.cal.entity.User;

public interface UserMapper extends SqlMapper{
	public void register(User u);//用户注册
	public User login(String account,String password);//用户登录
	public User findaccount(String account);
}
