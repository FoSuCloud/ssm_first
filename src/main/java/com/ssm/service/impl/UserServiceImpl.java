package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.domain.User;
import com.ssm.mapper.UserMapper;
import com.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource UserMapper userMapper;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userMapper.saveUser(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}
	
	@Override
	public void open_update(Integer id,String openid) {
		// TODO Auto-generated method stub
		userMapper.open_update(id,openid);
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.get(id);
	}
	
	@Override
	public User get_openid(String openid) {
		// TODO Auto-generated method stub
		return userMapper.get_openid(openid);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userMapper.delete(id);
	}

}
