package com.example.multitenancy.domain;

import java.util.Set;

public class Address {

	private Long id;

	private String city;

	private String street;

	private Set<User> user;

	public Address() {
		super();
	}

	public Address(Long id, String city, String street, Set<User> user) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", street=" + street + ", user=" + user + "]";
	}
	
}