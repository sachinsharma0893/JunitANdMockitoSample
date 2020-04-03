package com.example.multitenancy.service;

import java.util.List;

import com.example.multitenancy.domain.Role;

public interface RoleService {

	public List<Role> findAll();
	
	public Role findById(Long id);
	
	public Role saveRole(Role role);
	
	public void delete(Long id);
	
	public Role getDummyRole();
}
