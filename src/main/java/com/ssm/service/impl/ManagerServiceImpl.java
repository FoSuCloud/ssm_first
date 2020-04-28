package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.domain.Manager;
import com.ssm.mapper.ManagerMapper;
import com.ssm.service.ManagerService;


@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	@Resource ManagerMapper managerMapper;
	
	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return managerMapper.findAll();
	}

	@Override
	public void saveUser(Manager user) {
		// TODO Auto-generated method stub
		managerMapper.saveUser(user);
	}

	@Override
	public void update(Manager user) {
		// TODO Auto-generated method stub
		managerMapper.update(user);
	}
	

	@Override
	public Object get(Integer id) {
		// TODO Auto-generated method stub
		return managerMapper.get(id);
	}
	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		managerMapper.delete(id);
	}

	@Override
	public Object get_pass(String number, String password) {
		// TODO Auto-generated method stub
		return managerMapper.get_pass(number,password);
	}
}
