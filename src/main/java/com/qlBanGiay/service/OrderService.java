package com.qlBanGiay.service;

import java.util.List;

import com.qlBanGiay.entities.Orders;

public interface OrderService {
	public List<Orders> findAll();
	public Boolean create(Orders orders);
	
	public Orders findById(Integer id);
}
