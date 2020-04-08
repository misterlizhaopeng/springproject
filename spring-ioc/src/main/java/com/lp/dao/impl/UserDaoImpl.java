package com.lp.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.lp.dao.UserDao;
//@Primary
@Repository
public class UserDaoImpl implements UserDao {
	@Override
	public Integer calc(int a,int b) {
		return a+b;
	}

}
