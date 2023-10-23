package com.qlBanGiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.entities.Orders;

public interface OrderDetailRepo extends JpaRepository<OrderDetail , Integer>{
	public List<OrderDetail> findByOrders(Orders orders);
}
