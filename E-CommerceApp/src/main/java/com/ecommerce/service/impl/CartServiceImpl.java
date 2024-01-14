package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public void saveProductOnCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> getAllCartProduct() {
		return cartRepository.findAll();
	}

	@Override
	public void deleteProductFromCart(Long id) {
		cartRepository.deleteById(id);
	}

	@Override
	public void deleteAllProductFromCart() {
		cartRepository.deleteAll();
	}

	@Override
	public Cart getProduct(Long pid) {
		Cart cart=cartRepository.findById(pid).get();
		return cart;
	}

}
