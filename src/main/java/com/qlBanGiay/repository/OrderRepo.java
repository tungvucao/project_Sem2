package com.qlBanGiay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.entities.User;

public interface OrderRepo extends JpaRepository<Orders, Integer>{
}
