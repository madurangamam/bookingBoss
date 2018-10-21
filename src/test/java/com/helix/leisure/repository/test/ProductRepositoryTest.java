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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.helix.leisure.model.Event;
import com.helix.leisure.model.Product;
import com.helix.leisure.repository.ProductRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class ProductRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ProductRepository productRepository;
   
    

    @Test
    public void whenProductSave_thenEventShouldNotbeNull() {
    	Event product_event = new Event(new Date());
        //given
        
        Product product = new Product();
        product.setName("product1");
        product.setEvent(product_event);
        
        product_event.getProducts().add(product);

    	Event event= productRepository.save(product_event);
    	List<Event> foundEvent = productRepository.findAll();
    	assertNotNull(foundEvent);
    	assertEquals(event.getProducts().size(), 1);
    }
    
   /* @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        entityManager.persistAndFlush(createEvent(1L,"product 1",1, 23.7));
        //entityManager.persistAndFlush(createEvent(2L,"product 2",2, 28.7));
        //entityManager.persistAndFlush(createEvent(3L,"product 3",3, 28.8));
       // entityManager.flush();

        List<Event> allEmployees = productRepository.findAll();

        assertThat(allEmployees).hasSize(2).extracting(Event::getId).contains(2L,3L);
    }
    
    public Product createEvent(long id, String productName, int quantity, double salesAmount) {
    	Event event = new Event();
    	Product p = new Product();
    	Set<Product> productSet = new HashSet<>();
    	  	
    	p.setId(id);
    	p.setName(productName);
    	p.setQuantity(quantity);
    	p.setSale_amount(salesAmount);
    	//p.setEvent(event);
    	productSet.add(p);
    	
    	event.setTimestamp(new Date());
    	event.setId(id);
    	event.setProducts(productSet);
    	
    	return p;
    }*/
}


