package com.demo.eduardo.demo4.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping (path="/filtering")
	public SomeOtherBean retrieveSomeOtherBean () {
		return new SomeOtherBean("value1", "value2", "value3");
	}
}