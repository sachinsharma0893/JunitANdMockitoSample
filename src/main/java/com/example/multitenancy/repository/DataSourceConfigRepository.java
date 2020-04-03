package com.example.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multitenancy.domain.DataSourceConfig;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
	DataSourceConfig findByName(String name);
}