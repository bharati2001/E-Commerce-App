package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.OrderHistory;

public interface OrderHistoryService {

	List<OrderHistory> getAllOrderHistory();

	void saveOrderHistory(List<Cart> cartProducts);

}
