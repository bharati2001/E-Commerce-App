package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.OrderHistory;
import com.ecommerce.service.OrderHistoryService;

@RestController
@RequestMapping("/orderhistory")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@GetMapping("/allorderhistory")
	public List<OrderHistory> getAllOrderHistory(){
		List<OrderHistory> history=orderHistoryService.getAllOrderHistory();
		return history;
	}

}