package com.example.multitenancy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.multitenancy.domain.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testFindAllRoles() {
		List<Role> rolesList = roleRepository.findAll();
		assertEquals(4, rolesList.size());
	}

}
