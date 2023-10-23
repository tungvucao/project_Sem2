package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.entities.User;
import com.qlBanGiay.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo orderRepo;
	
	@Override
	public List<Orders> findAll() {
		// TODO Auto-generated method stub
		return this.orderRepo.findAll();
	}

	@Override
	public Boolean create(Orders orders) {
		try {
			this.orderRepo.save(orders);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Orders findById(Integer id) {
		// TODO Auto-generated method stub
		return this.orderRepo.findById(id).get();
	}
	

}
