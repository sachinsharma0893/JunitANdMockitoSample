package com.example.multitenancy.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.multitenancy.domain.Role;
import com.example.multitenancy.enums.RoleEnum;
import com.example.multitenancy.repository.RoleRepository;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

	@InjectMocks
	private RoleServiceImpl roleService;

	@Mock
	private RoleRepository roleRepository;

	private static final Role ADMIN_ROLE = new Role(1l, RoleEnum.ADMIN, "Admin");
	private static final Role LEAD_ROLE = new Role(2l, RoleEnum.LEAD, "Lead");
	private static final Role REVIEWER_ROLE  = new Role(3l, RoleEnum.REVIEWER, "Reviewer");
	private static final Role TESTER_ROLE = new Role(4l, RoleEnum.TESTER, "Tester");

	@Test
	public void validateRoleListData() {
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(ADMIN_ROLE);
		roleList.add(LEAD_ROLE);
		roleList.add(REVIEWER_ROLE);
		roleList.add(TESTER_ROLE);
		when(roleRepository.findAll()).thenReturn(roleList);
		List<Role> rolesList = roleService.findAll();
		assertEquals(4,rolesList.size());
	}
	
	@Test
	public void validateGetRoleById() {
		when(roleRepository.findById(1l)).thenReturn(Optional.of(ADMIN_ROLE));
		Role role = roleService.findById(1l);
		assertEquals(RoleEnum.ADMIN,role.getType());
	}
	
	@Test
	public void validateSaveRole() {
		when(roleRepository.save(TESTER_ROLE)).thenReturn(TESTER_ROLE);
		Role role = roleService.saveRole(TESTER_ROLE);
		assertEquals(RoleEnum.TESTER,role.getType());
	}
	
	@Test
	public void validateDelete() {
		verify(roleService, never()).delete(any());
	}

}
