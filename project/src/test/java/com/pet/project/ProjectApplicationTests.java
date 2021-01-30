package com.pet.project;

import com.pet.project.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MainController mainController;

	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(containsString("Hello guest")));
	}

	@Test
	public void loginTest() throws Exception {
		this.mockMvc.perform(get("/home"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http:localhost/login"));
	}

	@Test
	public void correctLoginTest() throws Exception {
		this.mockMvc.perform(formLogin().user("k").password("k"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"));
	}
}
