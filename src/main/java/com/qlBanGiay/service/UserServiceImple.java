package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.User;
import com.qlBanGiay.repository.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserServiceImple implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return this.userRepo.findByUserName(userName);
	}
	@Override
	public Boolean create(User user) {
		try {
			this.userRepo.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	@Override
	public Integer countUserName(String userName) {
		// TODO Auto-generated method stub
		return this.userRepo.countUserName(userName);
	}
}