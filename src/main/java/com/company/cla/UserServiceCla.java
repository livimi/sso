package com.company.cla;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.mapper.User;
import com.company.mapper.UserMapper;
import com.company.sec.Passport;

/**
 * @author Vimi
 * @category 用户的业务逻辑类
 */
public class UserServiceCla {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	Passport passport;
	
	public void addUser(User user) {
		user.setUserpass(passport.md5(user.getUserpass()));
		userMapper.addUser(user);
	}
	
	public List<User> findList(){
		return userMapper.findUsers();
	}
}
