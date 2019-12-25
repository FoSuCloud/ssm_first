package com.ssm.service;

import java.util.List;

import com.ssm.domain.Customer;

public interface CustomerService {
//	查询所有数据(业务接口)
	public List<Customer> findAll();
//	插入数据,返回为空，所以类型是void
	public void saveCustomer(Customer customer);
//	修改数据,返回为空，所以类型是void
	public void update(Customer customer);
	//根据id获取用户
	public Customer get(Integer id);
	//删除数据
	public void delete(Integer id);
}