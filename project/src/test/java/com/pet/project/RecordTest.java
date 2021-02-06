package com.pet.project;

import com.pet.project.models.Permission;
import com.pet.project.models.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }
    @Test
    @WithMockUser(username = "f", password = "$2a$12$3uheMmTAAdfFISyZ5PMD6eKowI3pKdhXNxd7ontahntwSD4mfzOMq" ,authorities = "user:read")
    public void recordsAvailableForUsers() throws Exception {
        mockMvc
                .perform(get("/records"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "f", password = "$2a$12$3uheMmTAAdfFISyZ5PMD6eKowI3pKdhXNxd7ontahntwSD4mfzOMq" ,authorities = "user:read")
    public void recordsAddAvailableForUsers() throws Exception {
        mockMvc
                .perform(get("/record-add"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "k", password = "$2a$12$vf22oHi7VDYXG./pNQeIi..Yg1m9QyL5cqa7ctjbdOpi4bm0Y/e8e" ,authorities = "user:read")
    public void userOrAdminIsAuthorizedToCreateRecord() throws Exception {

        mockMvc.perform(
                        post("/records/record-add")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("first_name", "titi")
                                .param("name", "toto")
                                .param("last_name", "toto")
                                .param("problem", "titi")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/records"));
    }
}