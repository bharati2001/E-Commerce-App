package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void createProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products=productRepository.findAll();
		return products;
	}

	@Override
	public Product findByPid(Long productId) {
		Product product=productRepository.findByPid(productId);
		return product;
	}

	@Override
	public void saveAll(List<Product> productsToSave) {
		productRepository.saveAll(productsToSave);
	}

}
