package com.example.multitenancy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multitenancy.domain.Role;
import com.example.multitenancy.service.RoleService;

@RestController
@RequestMapping(value = { "/api/v1/role" })
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getValidUser() {
		return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/dummy")
	public ResponseEntity<Role> getDummyUser() {
		return new ResponseEntity<>(roleService.getDummyRole(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
		return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id) {
		roleService.delete(id);
		return new ResponseEntity<>("Record Deleted", HttpStatus.OK);
	}

}
