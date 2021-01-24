package com.uni.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.example.restaurant.model.Order;
import com.uni.example.restaurant.model.OrderRepository;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRespository;
	@Override
	public void saveOrder(Order o) {
		// TODO Auto-generated method stub
		orderRespository.save(o);
	}

}
