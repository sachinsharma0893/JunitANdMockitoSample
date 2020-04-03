package com.example.multitenancy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multitenancy.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByUsername(String username);

}
