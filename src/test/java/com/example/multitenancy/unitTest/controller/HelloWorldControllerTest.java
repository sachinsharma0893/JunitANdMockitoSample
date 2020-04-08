package com.example.multitenancy.unitTest.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.multitenancy.controllers.HelloWorldController;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(value = HelloWorldController.class)
public class HelloWorldControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void helloWorldBasicTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(request).andReturn();
		System.out.println("***************** " + mvcResult.getResponse().getContentAsString());
		assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void helloWorldBasicTestDifferentForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Hello World")).andReturn();
	}

}
