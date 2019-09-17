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
	
	// Versioning by passing a parameter in the URI
	@GetMapping(value="/person/by-param", params="version=1")
	public PersonV1 getPersonByParamV1() {
		return new PersonV1("Ed Lozano by params");
	}
	
	@GetMapping(value="/person/by-param", params="version=2")
	public PersonV2 getPersonByParamV2() {
		return new PersonV2(new Name("Ed by params", "Lozano by params"));
	}
	
	// Versioning by passing a header in the request
	@GetMapping(value="/person/by-header", headers="X-API-VERSION=1")
	public PersonV1 getPersonByHeaderV1() {
		return new PersonV1("Ed Lozano by headers");
	}
	
	@GetMapping(value="/person/by-header", headers="X-API-VERSION=2")
	public PersonV2 getPersonByHeaderV2() {
		return new PersonV2(new Name("Ed by header", "Lozano by header"));
	}
	
	// MIME type versioning or Accept header versioning.
	@GetMapping(value="/person/produces", produces="application/eduardo.demo4.app-v1+json")
	public PersonV1 getPersonV1ByMimeType() {
		return new PersonV1("Ed Lozano by MIME type");
	}
	
	@GetMapping(value="/person/produces", produces="application/eduardo.demo4.app-v2+json")
	public PersonV2 getPersonV2ByMimeType() {
		return new PersonV2(new Name("Ed by MIME type", "Lozano by MIME type"));
	}
}
