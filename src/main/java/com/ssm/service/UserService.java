package com.ssm.service;

import java.util.List;

import com.ssm.domain.User;

public interface UserService {
//	查询所有数据(业务接口)
	public List<Object> findAll();
//	插入数据,返回为空，所以类型是void
	public void saveUser(User user);
//	修改数据,返回为空，所以类型是void
	public void update(User user);
	//根据id获取用户
	public Object get(Integer id);
	//删除数据
	public void delete(Integer id);
	//根据账号和密码判断用户登录
	public Object get_pass(String number,String password);
	public Object get_openid(String openid);
}
