package com.ssm.mapper;

import java.util.List;

import com.ssm.domain.Customer;


public interface CustomerMapper {
//	查询所有数据
	public List<Customer> findAll();
//	插入数据,返回为空，所以类型是void
	public void saveCustomer(Customer customer);
	//更新数据，返回为空
	public void update(Customer customer);
	//根据id获取数据
	public Customer get(Integer id);
	//删除数据
	public void delete(Integer id);
}
