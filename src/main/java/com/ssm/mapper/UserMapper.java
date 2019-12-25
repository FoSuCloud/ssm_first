package com.ssm.mapper;

import java.util.List;

import com.ssm.domain.User;


public interface UserMapper {
//	查询所有数据
	public List<User> findAll();
//	插入数据,返回为空，所以类型是void
	public void saveUser(User user);
	//更新数据，返回为空
	public void update(User user);
	//根据id获取数据
	public User get(Integer id);
	//删除数据
	public void delete(Integer id);
}
