package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Product;

public interface ProductService {

	void createProduct(Product product);

	List<Product> getAllProducts();

	Product findByPid(Long productId);

	void saveAll(List<Product> productsToSave);

}
