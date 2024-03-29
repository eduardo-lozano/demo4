package com.demo.eduardo.demo4.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.eduardo.demo4.exception.EntryRecordNotFoundException;
import com.demo.eduardo.demo4.exception.UserNotFoundException;

@RestController
public class SomeUserJpaResource {
	// This is for the new functionality
	@Autowired
	private SomeUserJpaRepository someUserJpaRepository;
	
	@Autowired
	private SomeTextEntryRecordJpaRepository someTextEntryRecordJpaRepository;
	
	@Autowired
	private MessageSource messageSource;  // Used for i18n
	
	// Get one
	@GetMapping("/jpa/users/{id}")
	public EntityModel<SomeUser> retrieveSomeUser(@PathVariable int id) {
		// If the findById doesn't find a record in the DB, the Optional avoids it to return null,
		// it returns the proper (empty?) object. 
		Optional<SomeUser> someUser = someUserJpaRepository.findById(id);
		if(!someUser.isPresent()) {
			throw new UserNotFoundException("id-" +id);
		}
		// HATEOAS simple example
		EntityModel<SomeUser> resource = new EntityModel<SomeUser>(someUser.get());
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsersList());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	// Get all
	@GetMapping("/jpa/users")
	public List<SomeUser> retrieveAllUsersList() {
		return someUserJpaRepository.findAll();
	}
	
	// Post
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createSomeUser(@Valid @RequestBody SomeUser user) {
		// Creates new user and returns that Bean object
		SomeUser savedUser = someUserJpaRepository.save(user);
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
	@DeleteMapping("/jpa/users/{id}")
	public void deleteSomeUser(@PathVariable int id) {
		// Returns nothing, and no need to handle exceptions here.
		someUserJpaRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/i18n-birthday-msg")
	public String i18nBirthdayMsg(@PathVariable int id ) {
		Optional<SomeUser> someUser = someUserJpaRepository.findById(id);
		if(!someUser.isPresent()) {
			throw new UserNotFoundException("id---" +id);
		}
		
		String i18nMessage = messageSource.getMessage("i18n.birthday.message", null, LocaleContextHolder.getLocale() );
		String fullBirthdayMessage = i18nMessage + someUser.get().getBirthDate().toString();
		
		return fullBirthdayMessage;
	}
	
	@GetMapping("/jpa/users/{id}/some-user-text-entries")
	public List<SomeTextEntryRecord> retrieveSomeTextEntryRecords(@PathVariable int id) {
		Optional<SomeUser> optionalUser = someUserJpaRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("id----" +id);
		}
		return optionalUser.get().getSomeTextEntryRecords();
	}
	
	@PostMapping("/jpa/users/{id}/some-user-text-entries")
	public ResponseEntity<Object> createSomeTextEntryRecord(@PathVariable int id, @RequestBody SomeTextEntryRecord someTextEntryRecord) {

		Optional<SomeUser> someUserOption = someUserJpaRepository.findById(id);
		if (someUserOption.isEmpty()) {
			throw new UserNotFoundException("id-----" +id);
		}
		// The entry comes without the user, you have to add it.
		SomeUser someUser = someUserOption.get();
		someTextEntryRecord.setSomeUser(someUser);
		// Save the SomeTextEntryRecord into the DB
		SomeTextEntryRecord savedTextEntryRecord = someTextEntryRecordJpaRepository.save(someTextEntryRecord);
		// Compose the URI of the new resource
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{entryId}")
					.buildAndExpand(savedTextEntryRecord.getId())
					.toUri();
		// Create a response with status CREATED, and in the response header will be a "location = URI"
		return ResponseEntity.created(uri).build();
	}
	
	// Get a single text entry by id
	@GetMapping("/jpa/some-user-text-entries/{entryId}")
	public SomeTextEntryRecord retrieveSomeTextEntryRecord(@PathVariable int entryId) {
		
		Optional<SomeTextEntryRecord> one = someTextEntryRecordJpaRepository.findById(entryId);
		if (one.isEmpty()) {
			throw new EntryRecordNotFoundException("Entry record id-" +entryId);
		}
		// Note this will not return the user id since that field is annotated in the bean as @JsonIgnore
		return one.get();
	}
}
