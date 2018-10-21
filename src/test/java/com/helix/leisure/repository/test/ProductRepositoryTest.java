package com.helix.leisure.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.helix.leisure.model.Event;
import com.helix.leisure.model.Product;
import com.helix.leisure.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void whenProductSave_thenEventShouldNotbeNull() {
		Event product_event = new Event(new Date());

		Product product = new Product();
		product.setName("product1");
		product.setEvent(product_event);

		product_event.getProducts().add(product);

		Event event = productRepository.save(product_event);
		List<Event> foundEvent = productRepository.findAll();
		assertNotNull(foundEvent);
		assertEquals(event.getProducts().size(), 1);
	}

}
