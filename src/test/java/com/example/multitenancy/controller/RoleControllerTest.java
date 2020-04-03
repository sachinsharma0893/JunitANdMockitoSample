package com.example.multitenancy.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.multitenancy.controllers.RoleController;
import com.example.multitenancy.domain.Role;
import com.example.multitenancy.enums.RoleEnum;
import com.example.multitenancy.service.RoleService;

@RunWith(SpringRunner.class)
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(value = RoleController.class)
public class RoleControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@MockBean
	private RoleService roleService; 

	@Test
	public void helloWorldBasicTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/role/dummy").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(request).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void dummyRoleFromService() throws Exception {
		when(roleService.getDummyRole()).thenReturn(new Role(1l,RoleEnum.ADMIN,"Admin"));
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/role/dummy").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json("{id:1,type:ADMIN,value:Admin}")).andReturn();
	}

	@Test
	public void roleFromDB() throws Exception {
		when(roleService.findAll()).thenReturn(Arrays.asList(new Role(1l,RoleEnum.ADMIN,"Admin"),new Role(2l,RoleEnum.TESTER,"Tester")));
		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/role").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json("[{id:1,type:ADMIN,value:Admin},{id:2,type:TESTER,value:Tester}]")).andReturn();
	}
}
