package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.OrderHistory;
import com.ecommerce.entity.Product;
import com.ecommerce.response.Response;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderHistoryService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@PostMapping("/saveproductoncart")
	public ResponseEntity<Response> saveProductOnCart(@RequestBody Product requestProduct){
		Cart cart=new Cart();
		cart.setTitle(requestProduct.getTitle());
		cart.setDescription(requestProduct.getDescription());
		cart.setPrice(requestProduct.getPrice());
		cart.setDiscountPercentage(requestProduct.getDiscountPercentage());
		cart.setRating(requestProduct.getRating());
		cart.setStock(requestProduct.getStock());
		cart.setBrand(requestProduct.getBrand());
		cart.setCategory(requestProduct.getCategory());
		cart.setThumbnail(requestProduct.getThumbnail());
		cart.setQuantity(1);
		cartService.saveProductOnCart(cart);
		Response response = new Response("success", "Product added to cart Successfully!!");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallcartproduct")
	public List<Cart> getAllCartProduct(){
		List<Cart> cartProducts=cartService.getAllCartProduct();
		return cartProducts;
	}
	
	@GetMapping("/deleteproductfromcart/{id}")
	public ResponseEntity<Response> deleteProductFromCart(@PathVariable Long id){
		cartService.deleteProductFromCart(id);
		Response response = new Response("success", "Product removed from cart Successfully!!");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/deleteallproduct")
	public ResponseEntity<Response> deleteAllProductFromCart(){
		List<Cart> cartProducts=cartService.getAllCartProduct();
		orderHistoryService.saveOrderHistory(cartProducts);
		cartService.deleteAllProductFromCart();
		Response response = new Response("success", "Order Placed Successfully!!");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
}