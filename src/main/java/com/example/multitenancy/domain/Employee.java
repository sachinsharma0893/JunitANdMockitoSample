package com.example.multitenancy.domain;

import java.util.Set;

public class Employee extends AbstractAuditingEntity {

	private Long id;

	private String name;

	private String position;

	private int salary;

	private int age;

	private Set<Address> primaryAddress;

	public Employee(Long id, String name, String position, int salary, int age, Set<Address> primaryAddress) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.age = age;
		this.primaryAddress = primaryAddress;
	}

	public Employee() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Address> getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Set<Address> primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
}