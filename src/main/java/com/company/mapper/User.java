package com.company.mapper;

/**
 * @author Vimi
 * @category 用户实体类
 */
public class User {
	
	private int userid;
	
	private String username;
	
	private String userpass;
	
	private String userimage;
	
	private int userrole;

	public User() {
		super();
	}
	
	

	public User(String username, String userpass, String userimage) {
		super();
		this.username = username;
		this.userpass = userpass;
		this.userimage = userimage;
	}



	public User(int userid, String username, String userpass, String userimage, int userrole) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpass = userpass;
		this.userimage = userimage;
		this.userrole = userrole;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUserimage() {
		return userimage;
	}

	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}

	public int getUserrole() {
		return userrole;
	}

	public void setUserrole(int userrole) {
		this.userrole = userrole;
	}
	
	
}
