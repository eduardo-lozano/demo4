package com.demo.eduardo.demo4.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SomeUserResource {
	
	@Autowired
	private SomeUserDaoService someUserDaoService;
	
	// Get one
	@GetMapping("/user/{id}")
	public SomeUser retrieveSomeUser(@PathVariable int id) {
		if (someUserDaoService.findSomeUser(id) == null) {
			throw new RuntimeException();
		}
		return someUserDaoService.findSomeUser(id);
	}
	
	// Get all
	@GetMapping("/users")
	public List<SomeUser> retrieveAllUsersList() {
		return someUserDaoService.findAll();
	}
	
	// Post
	@PostMapping("/user")
	public ResponseEntity<Object> createSomeUser(@RequestBody SomeUser user) {
		// Creates new user and returns that Bean object
		SomeUser savedUser = someUserDaoService.saveSomeUser(user);
		// Compose the URI of the new resource
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId())
					.toUri();
		// Create a response with status CREATED, and in the response header will be a "location = URI"
		return ResponseEntity.created(uri).build();
	}
	
	// Delete
	@DeleteMapping("/user/{id}")
	public void deleteSomeUser(@PathVariable int id) {
		someUserDaoService.deleteSomeUser(id);
	}
	

}
