package com.blog.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blog.api.controller.UserController;
import com.blog.api.domain.User;
import com.blog.api.service.UserService;

public class TestClass {
	
	
//	private MockMvc mockMvc;
//	@Mock
//    private UserService userService;
//	
//	@InjectMocks
//	private UserController userController;
//
//@Before
//public void setup() {
//    MockitoAnnotations.initMocks(this);
//    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//}
//
//@Test
//public void testGetAllUsers() throws Exception {
//	
//	User user1 = new User();
//	
//	user1.setFirstName("Sampada");
//	user1.setLastName("Panse");
//	user1.setUsername("saloni");
//	
//	User user2 = new User();
//	
//	user2.setFirstName("Atul");
//	user2.setLastName("Nipane");
//	user2.setUsername("atulnipane");
//
//	User user3 = userService.save(user1);
//	User user4 = userService.save(user2);
//	
//	
//	
//	
//	
//	when(userService.list()).thenReturn(Arrays.asList(user3, user4));
//	
//    mockMvc.perform(get("/users"))
//            .andDo(print())
//            .andExpect(status().isOk())
//            //.andExpect((ResultMatcher) jsonPath("$[0].id", isNotNull()))
//            .andExpect(jsonPath("$[0].firstName", is("Sampada")))
//            .andExpect(jsonPath("$[0].lastName", is("Panse")))
//            .andExpect(jsonPath("$[0].userName", is("saloni")))
//    		//.andExpect((ResultMatcher) jsonPath("$[1].id", isNotNull()))
//    		.andExpect(jsonPath("$[1].firstName", is("Atul")))
//    		.andExpect(jsonPath("$[1].lastName", is("Nipane")))
//    		.andExpect(jsonPath("$[1].userName", is("atulnipane")));
//}
//	
//
//
//@Test
//public void testGetUserById() throws Exception {
//	
//	User user1 = new User();
//	
//	user1.setFirstName("Sampada");
//	user1.setLastName("Panse");
//	user1.setUsername("saloni");
//
//
//	User user3 = userService.save(user1);
//	
//	
//	
//	
//	
//	when(userService.getUser(user3.getId())).thenReturn(user3);
//	
//    mockMvc.perform(get("/users/"+user3.getId()))
//            .andDo(print())
//            .andExpect(status().isOk())
//            //.andExpect((ResultMatcher) jsonPath("$[0].id", isNotNull()))
//            .andExpect(jsonPath("$[0].firstName", is("Sampada")))
//            .andExpect(jsonPath("$[0].lastName", is("Panse")))
//            .andExpect(jsonPath("$[0].userName", is("saloni")));
//}
//


	
}
