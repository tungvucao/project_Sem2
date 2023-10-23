package com.qlBanGiay.service;

import java.util.List;

import com.qlBanGiay.entities.User;

import jakarta.validation.Valid;

public interface UserService {
	public User findByUserName(String userName);
	public Boolean create(User user);
	public Integer countUserName(String userName);
}