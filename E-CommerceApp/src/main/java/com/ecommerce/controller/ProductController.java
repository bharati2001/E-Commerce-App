package com.ecommerce.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/product")
@EnableScheduling
public class ProductController {

	@Autowired
	private ProductService productService;

	@Scheduled(fixedRate = 900000)
	@GetMapping("/saveAllProducts")
	public void saveAllProducts() throws IOException {
		Request request = new Request.Builder().url("https://dummyjson.com/products").build();
		ObjectMapper objectMapper = new ObjectMapper();
		OkHttpClient httpClient = new OkHttpClient();
		try (Response response = httpClient.newCall(request).execute()) {
			if (response.isSuccessful()) {
				JsonNode responseBody = objectMapper.readTree(response.body().string());
				JsonNode products = responseBody.get("products");
				List<Product> productsToSave = new ArrayList<>();
				for (JsonNode productNode : products) {
					Long productId = productNode.get("id").asLong();
					Product findById = productService.findByPid(productId);
					if (findById == null) {
						Product newProduct = new Product();
						newProduct.setPid(productNode.get("id").asLong());
						newProduct.setTitle(productNode.get("title").asText());
						newProduct.setDescription(productNode.get("description").asText());
						newProduct.setPrice(productNode.get("price").asLong());
						newProduct.setDiscountPercentage(productNode.get("discountPercentage").asDouble());
						newProduct.setRating(productNode.get("rating").asDouble());
						newProduct.setStock(productNode.get("stock").asLong());
						newProduct.setBrand(productNode.get("brand").asText());
						newProduct.setCategory(productNode.get("category").asText());
						newProduct.setThumbnail(productNode.get("thumbnail").asText());
						productsToSave.add(newProduct);
					}
				}
				productService.saveAll(productsToSave);
			}
		  }
	    }
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		List<Product> products=productService.getAllProducts();
		return products;
	}
	
	@GetMapping("/getProduct")
	public Product getProduct(@RequestParam Long id) {
		Product product=productService.findByPid(id);
		return product;
	}
	
	@PostMapping("/createproduct")
	public void createUser(@RequestBody Product product){
		productService.createProduct(product);
//		Response response = new Response("success", "Product Created Successfully!!");
//	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
	
	
		


	