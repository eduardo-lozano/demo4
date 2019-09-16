package com.demo.eduardo.demo4.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping (path="/filtering")
	public SomeOtherBean retrieveSomeOtherBean() {
		return new SomeOtherBean("value1", "value2", "value3");
	}
	
	@GetMapping (path="/filtering-list")
	public List<SomeOtherBean> retrieveSomeOtherBeanList() {
		return Arrays.asList(new SomeOtherBean("value1-1", "value1-2", "value1-3"), new SomeOtherBean("value2-1", "value2-2", "value2-3"));
	}
}