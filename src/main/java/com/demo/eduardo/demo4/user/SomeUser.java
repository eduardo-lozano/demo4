package com.demo.eduardo.demo4.user;

import java.util.Date;

public class SomeUser {
	
	private Integer id;
	private String name;
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
