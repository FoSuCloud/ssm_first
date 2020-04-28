package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.domain.Manager;

public interface ManagerMapper {
//	查询所有数据
	public List<Object> findAll();
//	插入数据,返回为空，所以类型是void
	public void saveUser(Manager user);
	//更新数据，返回为空
	public void update(Manager user);
	//根据id获取数据
	public Object get(Integer id);
	//删除数据
	public void delete(Integer id);
	// 根据账号密码判断用户
	public Object get_pass(@Param("number")String number,@Param("password")String password);
}
