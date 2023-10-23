package com.qlBanGiay.service;

import java.util.List;

import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.entities.Orders;

public interface OrderDetailService {
	
	public List<OrderDetail> findByOrders(Orders orders);
	public Boolean add(OrderDetail orderDetail);
}
