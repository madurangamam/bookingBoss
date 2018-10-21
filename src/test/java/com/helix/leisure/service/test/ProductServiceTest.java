package com.helix.leisure.service.test;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.helix.leisure.model.Event;
import com.helix.leisure.model.Product;
import com.helix.leisure.repository.ProductRepository;
import com.helix.leisure.service.IProductService;
import com.helix.leisure.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@TestConfiguration
    static class ProductServiceTestContextConfoguration {
        @Bean
        public IProductService productService() {
            return new ProductService();
        }
    }
	
	@Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;
    
    @Before
    public void setUp() {
    	
    	Event event = new Event();
    	Product p = new Product();
    	Set<Product> productSet = new HashSet<>();
    	  	
    	p.setId(1L);
    	p.setName("Test");
    	p.setQuantity(10);
    	p.setSale_amount(2005.74);
    	p.setEvent(event);
    	productSet.add(p);
    	
    	event.setTimestamp(new Date());
    	event.setId(1L);
    	event.setProducts(productSet);
    	
    	List<Event> eventList = new ArrayList<>();
    	eventList.add(event);

    	Mockito.when(productRepository.findAll()).thenReturn(eventList);
    	
    }
    
    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        
        createEvent() ;

        List<Event> allProducts = productService.getAllProducts();
        verifyFindAllProductsAreCalledOnce();
        assertThat(allProducts).hasSize(1).extracting(Event::getId).contains(createEvent().getId());
    }
    
    private void verifyFindAllProductsAreCalledOnce() {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(productRepository);
    }
    
    public Event createEvent() {
    	Event event = new Event();
    	Product p = new Product();
    	Set<Product> productSet = new HashSet<>();
    	  	
    	p.setId(1L);
    	p.setName("Test");
    	p.setQuantity(10);
    	p.setSale_amount(2005.74);
    	p.setEvent(event);
    	productSet.add(p);
    	
    	event.setTimestamp(new Date());
    	event.setId(1L);
    	event.setProducts(productSet);
    	
    	return event;
    }
}
