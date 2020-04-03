package com.example.multitenancy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multitenancy.dto.TenantsDto;

@RestController
@RequestMapping(value = { "api/v1/tenant" })
public class TenantController {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/register")
	public ResponseEntity<TenantsDto> addNewTenant(TenantsDto tenantDto) {
		System.out.print("*************  signup call executed  ****************");
		return new ResponseEntity<TenantsDto>(new TenantsDto(), HttpStatus.OK);
	}

}
