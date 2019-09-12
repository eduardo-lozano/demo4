package com.demo.eduardo.demo4.user;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.eduardo.demo4.exception.UserNotFoundException;

@RestController
public class SomeUserResource {
	
	@Autowired
	private SomeUserDaoService someUserDaoService;
	@Autowired
	private MessageSource messageSource;  // Used for i18n
	
	// Get one
	@GetMapping("/users/{id}")
	public EntityModel<SomeUser> retrieveSomeUser(@PathVariable int id) {
		SomeUser someUser = someUserDaoService.findSomeUser(id);
		if(someUser == null) {
			throw new UserNotFoundException("id-" +id);
		}
		// HATEOAS simple example
		EntityModel<SomeUser> resource = new EntityModel<SomeUser>(someUser);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsersList());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	// Get all
	@GetMapping("/users")
	public List<SomeUser> retrieveAllUsersList() {
		return someUserDaoService.findAll();
	}
	
	// Post
	@PostMapping("/users")
	public ResponseEntity<Object> createSomeUser(@Valid @RequestBody SomeUser user) {
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
	@DeleteMapping("/users/{id}")
	public void deleteSomeUser(@PathVariable int id) {
		SomeUser someUser = someUserDaoService.deleteSomeUserById(id);
		if (someUser == null) {
			throw new UserNotFoundException("id--" +id);
		}
	}
	
	@GetMapping("/users/{id}/i18n-birthday-msg")
	public String i18nBirthdayMsg(@PathVariable int id ) {
		SomeUser someUser = someUserDaoService.findSomeUser(id);
		if(someUser == null) {
			throw new UserNotFoundException("id---" +id);
		}
		
		String i18nMessage = messageSource.getMessage("i18n.birthday.message", null, LocaleContextHolder.getLocale() );
		String fullBirthdayMessage = i18nMessage + someUser.getBirthDate().toString();
		
		return fullBirthdayMessage;
	}
	
}
