package com.cal.mapper;

import com.cal.entity.User;

public interface UserMapper extends SqlMapper{
	public void register(User u);//�û�ע��
	public User login(String account,String password);//�û���¼
	public User findaccount(String account);
}
