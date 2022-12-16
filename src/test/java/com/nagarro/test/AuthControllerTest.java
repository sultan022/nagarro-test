/*
package com.nagarro.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nagarro.test.controller.AuthController;
import com.nagarro.test.security.AuthEntryPointJwt;
import com.nagarro.test.security.JwtUtils;
import com.nagarro.test.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = AuthController.class)
class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserDetailsServiceImpl userDetailsService;

	@MockBean
	AuthEntryPointJwt authEntryPointJwt;

	@MockBean
	JwtUtils jwtUtils;

	@Test
	void contextLoads() {
	}

	@Test
	public void loginTest() throws Exception {


		ObjectNode mainNode = new ObjectMapper().createObjectNode();
		mainNode.put("username", "admin");
		mainNode.put("password", "admin");

		*/
/*RequestBuilder requestBuilder = post("/api/auth/login")
				.accept(MediaType.APPLICATION_JSON).content(mainNode.toString())
				.contentType(MediaType.APPLICATION_JSON);*//*


		mockMvc.perform(post("/api/auth/login")
						.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
						.content(mainNode.toString()))
				.andExpect(status().is2xxSuccessful());



	*/
/*	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());*//*


	}

}
*/
