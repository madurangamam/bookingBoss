package com.helix.leisure.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helix.leisure.model.Event;
import com.helix.leisure.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProducts")
	public List<Event> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/putproducts")
	public Event createProduct(@Valid @RequestBody Event event) {

		return productService.createProduct(event);
	}

}
