package com.company.service;

import java.util.List;

import com.company.mapper.User;

/**
 * @author Vimi
 * @category 用户业务逻辑接口
 */
public interface UserService {
	
	public User login(String name,String pass);
	
	public List<User> findList();
}
