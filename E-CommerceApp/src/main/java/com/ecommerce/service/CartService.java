package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Cart;

public interface CartService {

	void saveProductOnCart(Cart cart);

	List<Cart> getAllCartProduct();

	void deleteProductFromCart(Long id);

	void deleteAllProductFromCart();

	Cart getProduct(Long pid);


}
