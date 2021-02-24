package com.pet.project;

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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminTest {
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
    public void adminUnavailableForAll() throws Exception {
        mockMvc
                .perform(get("/user-list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void adminUnavailableForUserNonAdmin() throws Exception {
        mockMvc
                .perform(get("/user-list"))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "k", password = "$2a$12$vf22oHi7VDYXG./pNQeIi..Yg1m9QyL5cqa7ctjbdOpi4bm0Y/e8e" ,authorities = "user:edit")
    public void adminAvailableForAdmin() throws Exception {
        mockMvc
                .perform(get("/user-list"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "k", password = "$2a$12$vf22oHi7VDYXG./pNQeIi..Yg1m9QyL5cqa7ctjbdOpi4bm0Y/e8e" ,authorities = "user:edit")
    public void adminCanEditUser() throws Exception {
        mockMvc
                .perform(
                        post("/user/23/edit")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("username", "ff")
                            .param("status", "BANNED"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-list"));

    }
    @Test
    @WithMockUser(username = "k", password = "$2a$12$vf22oHi7VDYXG./pNQeIi..Yg1m9QyL5cqa7ctjbdOpi4bm0Y/e8e" ,authorities = "user:change_role")
    public void adminCanChangeRole() throws Exception {
        mockMvc
                .perform(
                        post("/user/23/change-role")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("role", "MODERATOR"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-list"));

    }
}