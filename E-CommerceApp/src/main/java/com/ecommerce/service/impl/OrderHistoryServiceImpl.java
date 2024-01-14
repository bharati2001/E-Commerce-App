package com.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.OrderHistory;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderHistoryRepository;
import com.ecommerce.service.OrderHistoryService;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Override
	public List<OrderHistory> getAllOrderHistory() {
		List<OrderHistory> history=orderHistoryRepository.findAll();
		return history;
	}

	@Override
	public void saveOrderHistory(List<Cart> cartProducts) {
		List<OrderHistory> orderHistoryToSave=new ArrayList<>();
		for (Cart cartNode : cartProducts) {
			OrderHistory orderHistory=new OrderHistory();
			orderHistory.setId(cartNode.getId());
			orderHistory.setTitle(cartNode.getTitle());
			orderHistory.setDescription(cartNode.getDescription());
			orderHistory.setPrice(cartNode.getPrice());
			orderHistory.setDiscountPercentage(cartNode.getDiscountPercentage());
			orderHistory.setRating(cartNode.getRating());
			orderHistory.setStock(cartNode.getStock());
			orderHistory.setBrand(cartNode.getBrand());
			orderHistory.setCategory(cartNode.getCategory());
			orderHistory.setThumbnail(cartNode.getThumbnail());
			orderHistory.setQuantity(cartNode.getQuantity());
			orderHistoryToSave.add(orderHistory);
		}
		orderHistoryRepository.saveAll(orderHistoryToSave);
	}
	
}
