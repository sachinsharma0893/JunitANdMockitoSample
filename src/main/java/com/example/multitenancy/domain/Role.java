package com.example.multitenancy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.multitenancy.enums.RoleEnum;

@Entity
@Table(name = "ref_role")
public class Role {

	@Id
	@SequenceGenerator(name = "role_seq", initialValue = 1, allocationSize = 1, sequenceName = "role_seq")
	@GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column
	@Enumerated(EnumType.STRING)
	private RoleEnum type;

	@Column
	private String value;

	public Role(Long id, RoleEnum type, String value) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", type=" + type + ", value=" + value + "]";
	}

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getType() {
		return type;
	}

	public void setType(RoleEnum type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}