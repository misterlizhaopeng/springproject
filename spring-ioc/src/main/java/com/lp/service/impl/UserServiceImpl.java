package com.lp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lp.dao.UserDao;
import com.lp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Qualifier("userDao-BEAN01")
	@Autowired
	private UserDao userDao01;
	
	@Override
	public Integer calc(int a,int b) {
		return userDao01.calc(a, b);
	}
}