package com.example.multitenancy.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "t_user")
public class User extends AbstractAuditingEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 1, sequenceName = "user_seq")
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private Boolean isActive;

	@ManyToMany
	@JoinTable(name = "bt_user_role", joinColumns = {
			@JoinColumn(name = "userid", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roleid", referencedColumnName = "id") })
	private Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getType().toString()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isActive;
	}

}
