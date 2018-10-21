package com.helix.leisure.service;

import java.util.List;

import com.helix.leisure.model.Event;

public interface IProductService {

	List<Event> getAllProducts();

	Event createProduct(Event event);

}