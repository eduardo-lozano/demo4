package com.demo.eduardo.demo4.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SomeTextEntryRecord {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private SomeUser someUser;
	
	
	public SomeTextEntryRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SomeTextEntryRecord(String description, SomeUser someUser) {
		super();
		this.description = description;
		this.someUser = someUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SomeUser getSomeUser() {
		return someUser;
	}
	public void setSomeUser(SomeUser someUser) {
		this.someUser = someUser;
	}

	@Override
	public String toString() {
		return "SomeTextEntryRecord [id=" + id + ", description=" + description + "]";
	}
}
