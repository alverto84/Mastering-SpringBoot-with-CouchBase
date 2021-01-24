package com.uni.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.example.restaurant.model.Order;
import com.uni.example.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/save")
	public void saveOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
	}
}
