package com.example.multitenancy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multitenancy.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public List<Role> findAll();

}
