package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Vimi
 * @category 用户实体映射类
 */
@Mapper
public interface UserMapper {
	
	@Select("select * from userinfo where username=#{username}")
	public List<User> findUsersByName(@Param("username") String username);
	
	@Update("update userinfo set userpass=#{user.userpass},userrole=#{user.userrole} where username=#{user.username}")
	public void updateUser(@Param("user") User user);
	
	@Update("delete from userinfo where username=#{username}")
	public void deleteUserByName(@Param("username") String username);
	
	@Update("insert into userinfo(username,userpass,userrole,userheadimage) values(#{user.username},#{user.userpass},#{user.userrole},#{user.userimage})")
	public void addUser(@Param("user") User user);
	
	@Select("select * from userinfo where username=#{username} and userpass=#{userpass}")
	public List<User> findUserByNameAndPass(@Param("username") String username,@Param("userpass") String userpass);
	
	@Select("select * from userinfo order by userid desc")
	public List<User> findUsers();
	
}
