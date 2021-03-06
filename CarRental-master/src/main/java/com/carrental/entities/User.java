package com.carrental.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -4062784936522642553L;

	public static final String ATTRIBUTE_ID = "id";
	public static final String ATTRIBUTE_NAME = "name";
	public static final String ATTRIBUTE_PASSWORD = "password";
	public static final String ATTRIBUTE_ROLE = "role";
	public static final String ATTRIBUTE_EMAIL = "email";

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY) //SEQUENCE, generator = "order_seq")
	//@SequenceGenerator(name = "rental_seq", sequenceName = "users_id_seq", allocationSize = 10)
	//@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "email")
	private String email;

	public User() {

	}

	public User(Long id, String name, String password, String role, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", email=" + email + "]";
	}

}
