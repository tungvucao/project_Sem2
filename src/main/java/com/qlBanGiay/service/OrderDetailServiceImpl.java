package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.repository.OrderDetailRepo;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepo orderDetailRepo;
	@Override
	public Boolean add(OrderDetail orderDetail) {
		try {
			this.orderDetailRepo.save(orderDetail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<OrderDetail> findByOrders(Orders orders) {
		// TODO Auto-generated method stub
		return this.orderDetailRepo.findByOrders(orders);
	}
}
