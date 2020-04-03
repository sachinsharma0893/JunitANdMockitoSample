package com.example.multitenancy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.multitenancy.domain.Role;
import com.example.multitenancy.enums.RoleEnum;
import com.example.multitenancy.repository.RoleRepository;
import com.example.multitenancy.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role findById(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		return optionalRole.get();
	}

	@Override
	public Role getDummyRole() {
		return new Role(1l, RoleEnum.ADMIN, "Admin");
	}

}
