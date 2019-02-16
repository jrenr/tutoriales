package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class FormloginApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	@Before
	public void before() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.alwaysDo(print())
				.build();
	}
	
	@Test
	public void formLogin_InvalidUser_BadCredentialsException() throws Exception {
		
		MockHttpSession session = new MockHttpSession();
		
		MockHttpServletRequestBuilder login = login().session(session);
		
		mvc
			.perform(login)
			.andExpect(status().is3xxRedirection());
		
		Exception exception = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		assertThat(exception).isNotNull();
		assertThat(exception).isInstanceOf(BadCredentialsException.class);
		
	}
	
	private MockHttpServletRequestBuilder login() {
		
		return post("/login")
				.param("username", "invalid_user")
				.param("password", "some_password")
				.with(csrf());
	}

}

