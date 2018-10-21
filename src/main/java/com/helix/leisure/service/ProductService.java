package com.helix.leisure.service;

import java.util.List;

import com.helix.leisure.model.Event;

public interface ProductService {

	List<Event> getAllProducts();

	Event createProduct(Event event);

}