package com.pet.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.alwaysDo(print())
				.build();
	}

	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/login"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Login")));


	}
	@Test
	public void loginTest() throws Exception{
		this.mockMvc.perform(get("/main"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	public void loginAvailableForAll() throws Exception {
		mockMvc
				.perform(get("/login"))
				.andExpect(status().isOk());
	}

}
