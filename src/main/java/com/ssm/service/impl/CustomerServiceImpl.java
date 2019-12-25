package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.domain.Customer;
import com.ssm.mapper.CustomerMapper;
import com.ssm.service.CustomerService;

/**
 * @author 16141
 *
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
//	注入业务对象
	@Resource
	public CustomerMapper customerMapper;
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerMapper.findAll();
	}
	
//	插入数据,返回为空，所以类型是void,void所以是不return的
	@Override
	public void saveCustomer(Customer customer) {
		customerMapper.saveCustomer(customer);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerMapper.update(customer);
	}

	@Override
	public Customer get(Integer id) {
		// TODO Auto-generated method stub
		return customerMapper.get(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		customerMapper.delete(id);
	};
	
}
