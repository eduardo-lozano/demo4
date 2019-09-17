package com.demo.eduardo.demo4.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("EduardoDemoBeanFilter")   // Don't forget to add the filter name in here, or it won't work and you won't know why.
public class SomeOtherBean {
	
	public String firstField;
	public String secondField;
	public String thirdField;
	
	public SomeOtherBean(String firstField, String secondField, String thirdField) {
		super();
		this.firstField = firstField;
		this.secondField = secondField;
		this.thirdField = thirdField;
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

	public String getThirdField() {
		return thirdField;
	}

	public void setThirdField(String thirdField) {
		this.thirdField = thirdField;
	}

}
