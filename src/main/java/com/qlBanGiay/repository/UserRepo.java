package com.qlBanGiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlBanGiay.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public User findByUserName(String userName);
	@Query("SELECT COUNT(*) FROM User u WHERE u.userName = ?1")
	public Integer countUserName(String userName);
}