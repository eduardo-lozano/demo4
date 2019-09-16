package com.demo.eduardo.demo4.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SomeOtherBean {
	
	public String firstField;
	public String secondField;
	@JsonIgnore
	public String thirdFiled;
	
	public SomeOtherBean(String firstField, String secondField, String thirdFiled) {
		super();
		this.firstField = firstField;
		this.secondField = secondField;
		this.thirdFiled = thirdFiled;
	}

	public String getFirstField() {
		return firstField;
	}

	public void setFirstField(String firstField) {
		this.firstField = firstField;
	}

	public String getSecondField() {
		return secondField;
	}

	public void setSecondField(String secondField) {
		this.secondField = secondField;
	}

	public String getThirdFiled() {
		return thirdFiled;
	}

	public void setThirdFiled(String thirdFiled) {
		this.thirdFiled = thirdFiled;
	}

}
