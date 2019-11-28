package com.company.cla;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.mapper.User;
import com.company.mapper.UserMapper;
import com.company.sec.Passport;
import com.company.service.OnlineUserInfo;
import com.company.service.SSOTokenService;

/**
 * @author Vimi
 *
 */
@Service
public class LoginServiceCla {

	@Autowired
	@Qualifier("tokenService")
	SSOTokenService tokenService;

	@Autowired
	Passport passport;

	@Autowired
	UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 用户名错误的标识
	 */
	public static final int WRONG_USERNAME = 0;
	/**
	 * 密码错误的标识
	 */
	public static final int WRONG_PASSWORD = 1;
	/**
	 * 用户已经在线不能重复登录的标识
	 */
	public static final int USER_ALREADY_ONLINE = 2;
	/**
	 * 用户被锁定标识
	 */
	public static final int WRONG_LOCKED = 3;
	/**
	 * 其他错误的标识
	 */
	public static final int WRONG_OTHER = 4;
	/**
	 * 登录成功标识
	 */
	public static final int LOGIN_OK = 5;

	/**
	 * 登录成功的用户信息
	 */
	private User loginUser = null;

	/**
	 * 获取登录成功的用户信息的方法
	 * 
	 * @return 返回登录成功的用户信息
	 */
	public User getLoginUser() {
		// 返回用户信息
		return loginUser;
	}

	/**
	 * 登录的判定方法
	 * 
	 * @param Info 用户输入的需判定登录用户信息
	 * @return 登录判定结果标识值
	 */
	public int login(User info) {

		// 如果某个在线用户的用户名和用户登录的用户名相同，说明试图登录的用户已经在线
		if (tokenService.isUserOnline(info.getUsername())) {
			// 返回错误标识
			return USER_ALREADY_ONLINE;
		}

		info.setUserpass(passport.md5(info.getUserpass()));

		// 获取带有连接池的数据库模版操作工具对象

		List<User> userList = userMapper.findUsersByName(info.getUsername());
		// 判定是否查询到数据
		switch (userList.size()) {
		// 如果没有查询到数据，说明用户名不存在
		case 0:
			// 返回用户名错误的标识
			return WRONG_USERNAME;
		// 如果查询到数据
		case 1:
			// 获取数据库中的用户信息
			User dbUser = userList.get(0);

			// System.out.println(info.getLocked());
			// // 如果用户已经被锁定
			// if (dbUser.getLocked() == 1) {
			// // 保存登录用户信息
			// loginUser = dbUser;
			// // 返回用户已经被锁定的标识
			// return WRONG_LOCKED;
			// }
			// 如果用户密码匹配
			if (info.getUserpass().equals(dbUser.getUserpass())) {
				// 保存登录用户信息
				loginUser = dbUser;
				// 返回登录成功标识
				String key = createReadomKey();
				// HttpServletResponse response =
				// ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				OnlineUserInfo onlineInfo = new OnlineUserInfo();
				onlineInfo.setLastResetTime(System.currentTimeMillis());
				onlineInfo.setUsername(info.getUsername());
				onlineInfo.setRole(info.getUserrole());
				onlineInfo.setToken(key);
				HttpSession session = request.getSession();
				session.setAttribute("key", key);
				tokenService.addUserToken(key, onlineInfo);

				return LOGIN_OK;
				// 如果密码不匹配
			} else {
				// 返回密码错误标识
				return WRONG_PASSWORD;
			}
			// 其他情况
		default:
			// 返回其他错误标识
			return WRONG_OTHER;

		}

	}

	/**
	 * 返回随机的唯一的用户令牌
	 */
	public String createReadomKey() {
		String seed = UUID.randomUUID().toString();
		return passport.md5(seed);
	}

}
