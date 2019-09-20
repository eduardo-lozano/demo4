package com.demo.eduardo.demo4.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

// This declares this Bean as also a DB entity managed with JPA
@Entity
public class SomeUser {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="This message would be shown to notice that the name must have 2 or more characters.")
	private String name;
	@Past
	private Date birthDate;
	
	protected SomeUser() {
		
	}

	public SomeUser(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "SomeUser [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
