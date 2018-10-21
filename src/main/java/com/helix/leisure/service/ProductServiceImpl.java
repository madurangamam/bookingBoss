package com.helix.leisure.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helix.leisure.model.Event;
import com.helix.leisure.model.Product;
import com.helix.leisure.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Event> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Event createProduct(Event event) {
		Event product_event = new Event(event.getTimestamp());

		// Create Products
		Set<Product> productSet = event.getProducts();

		for (Product product : productSet) {
			Product p = new Product(product.getName(), product.getQuantity(), product.getSale_amount());
			p.setEvent(product_event);
			product_event.getProducts().add(p);
		}

		return productRepository.save(product_event);
	}

}
