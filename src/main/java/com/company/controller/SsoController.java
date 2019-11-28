package com.company.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.cla.LoginServiceCla;
import com.company.cla.UserServiceCla;
import com.company.mapper.User;
import com.company.util.SSOFileUpload;

/**
 * @author Vimi
 * @category 控制器
 */
@Controller
public class SsoController {
	
	@Autowired
	LoginServiceCla loginService;
	
	@Autowired
	UserServiceCla userService;
	
	@Autowired
	SSOFileUpload sSOFileUpload;
	
	@RequestMapping(value="/ssologin",method=RequestMethod.GET)
	public String ssologin(@RequestParam("from")String from,HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("from", from);
		return "login";
	}
	
	@RequestMapping(value="/ssologin",method=RequestMethod.POST)
	public String ssologin(@RequestParam("userAccount") String username,@RequestParam("userPass") String password,HttpServletRequest request) {
		User user = new User();
		user.setUsername(username);
		user.setUserpass(password);
		int number = loginService.login(user);
		System.out.println("登录状态为："+number);
		//执行登录判定
		switch(number) {
		case LoginServiceCla.WRONG_USERNAME:
			//在作用域中保存用户名不存在的错误提示
			request.setAttribute("ERROR_MSG", "用户名不存在！");
			//在作用域中保存用户填写的信息
			request.setAttribute("USER_INFO", user);
			//跳转回登录页面
			return "login";
			
		case LoginServiceCla.WRONG_PASSWORD:
			//在作用域中保存密码错误的错误提示
			request.setAttribute("ERROR_MSG", "用户密码不匹配！");
			//在作用域中保存用户填写的信息
			request.setAttribute("USER_INFO", user);
			//跳转回登录页面
			return "login";
			
			//如果登录成功
		case LoginServiceCla.LOGIN_OK:
			//在会话信息中保存用户的详细信息
			System.out.println("登录成功");
			return "redirect:userlist";
			
			//如果用户已经被锁定
		case LoginServiceCla.WRONG_LOCKED:
			//在作用域中保存用户被锁定的错误提示
			request.setAttribute("ERROR_MSG", "该用户已经被锁定！");
			//在作用域中保存用户填写的信息
			request.setAttribute("USER_INFO", loginService.getLoginUser());
			//跳转回登录页面
			return "login";
			
			//如果用户已经在线
		case LoginServiceCla.USER_ALREADY_ONLINE:
			//在作用域中保存用户已经在线的错误提示
			request.setAttribute("ERROR_MSG", "该用户已经在线，不能重复登录！");
			//在作用域中保存用户填写的信息
			request.setAttribute("USER_INFO", user);
			//跳转回登录页面
			return "login";
		}
		return "";
	}
	
	@RequestMapping(value="/ssoregist",method=RequestMethod.GET)
	public String ssoregist(HttpServletRequest request) {
		return "regist";
	}
	
	@RequestMapping(value="/ssoregist",method=RequestMethod.POST)
	public String ssoregist(@RequestParam("userAccount") String username,@RequestParam("userPass") String password,HttpServletRequest request){
		//头像上传
		String headimage = null;
		try {
			headimage = sSOFileUpload.upload(request);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user = new User(username,password,headimage);
		userService.addUser(user);
		return "registok";
	}
	
	@RequestMapping("/ok")
	public String ok() {
		return "loginok";
	}
	
}
