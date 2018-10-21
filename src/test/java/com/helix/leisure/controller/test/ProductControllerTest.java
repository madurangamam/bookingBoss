package com.helix.leisure.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.helix.leisure.BookingBossAppRunner;
import com.helix.leisure.model.Event;
import com.helix.leisure.model.Product;
import com.helix.leisure.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BookingBossAppRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ProductRepository productRepository;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void whenValidInputThenCreateProduct() throws IOException, Exception {
		createTestEvent();
		mvc.perform(post("/putproducts").with(httpBasic("admin1", "secret1")).contentType(MediaType.APPLICATION_JSON)
				.content(convertToJasonString(retieveEvent())));

		List<Event> found = productRepository.findAll();
		assertThat(found).extracting(Event::getId).contains(1L);
	}

	@Test
	public void whenProductIsGivenValidateResponse() throws Exception {

		createTestEvent();
		mvc.perform(get("/getProducts").with(httpBasic("admin1", "secret1")).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1)))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].products.[0].name", is("Test")));
	}

	private void createTestEvent() {

		productRepository.saveAndFlush(retieveEvent());
	}

	private String convertToJasonString(Event obj) throws JsonProcessingException {
		String jasonString = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		jasonString = ow.writeValueAsString(obj);

		return jasonString;
	}
	
	/**
	 * Set values to event object
	 * @return Event object
	 */
	public Event retieveEvent() {
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
