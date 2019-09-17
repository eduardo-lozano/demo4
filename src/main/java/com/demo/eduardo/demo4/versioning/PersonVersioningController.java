package com.demo.eduardo.demo4.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	// Simplest way of versioning is to expose different methods in different URIs separated.
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Ed Lozano");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Ed", "Lozano"));
	}
}
