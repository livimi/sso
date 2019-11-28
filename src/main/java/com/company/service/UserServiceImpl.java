package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.mapper.User;
import com.company.mapper.UserMapper;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User login(String name, String pass) {
		List<User> list = userMapper.findUserByNameAndPass(name, pass);
		return list!=null?list.get(1):null;
	}

	@Override
	public List<User> findList() {
		List<User> list = userMapper.findUsers();
		return list;
	}

}
