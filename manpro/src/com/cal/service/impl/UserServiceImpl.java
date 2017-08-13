package com.cal.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cal.entity.User;
import com.cal.mapper.UserMapper;
import com.cal.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper um;

	@Override
	public void register(User u) {
		u.setUid(UUID.randomUUID().toString());
		um.register(u);
	}

	@Override
	public User login(String account, String password) {
		return um.login(account, password);
	}

	@Override
	public User findaccount(String account) {
		return um.findaccount(account);
	}
}
