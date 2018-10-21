package com.helix.leisure.controller;


import com.helix.leisure.model.Event;
import com.helix.leisure.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


import javax.validation.Valid;

@RestController
public class ProductController {

    /*@Autowired
    ProductRepository productRepository;*/
	@Autowired
   IProductService productService;
    
    @GetMapping("/getProducts")
    public List<Event> getAllProducts() {
        return productService.getAllProducts();
    }

    
    @PostMapping("/putproducts")
    public Event createProduct(@Valid @RequestBody Event event) {
    	
    	return productService.createProduct(event);
    }
   
    

}
