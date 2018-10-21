package com.helix.leisure.security.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerSecurityTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test authentication with valid Credentials
	 * 
	 */
	@Test
	public void accessWithValidCredentials() throws Exception {
		this.mockMvc.perform(get("/getProducts").with(httpBasic("admin1", "secret1"))).andExpect(status().isOk());
	}

	/**
	 * Test authentication with invalid Credentials
	 * 
	 */
	@Test
	public void accessWithInValidCredentials() throws Exception {
		this.mockMvc.perform(get("/getProducts").with(httpBasic("admin1", "secret11")))
				.andExpect(status().is4xxClientError());
	}

}
