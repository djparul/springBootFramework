package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.repository.ItemRepository;
import com.appsdeveloperblog.app.ws.ul.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ul.model.request.UserDetails;
import com.appsdeveloperblog.app.ws.ul.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
    ItemRepository userDetailsRepo;
	
	Map<String, UserRest> users;

	//get all users by field
//	@GetMapping
//	public String getUsers(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit,
//			@RequestParam(value = "sort") int sort) {
//		return "get users was calledwith page = " + page + " and limit = " + limit;
//	}
	
	@GetMapping()
	public ResponseEntity<List<UserRest>> getUsers() {
		List<UserDetails> users = userDetailsRepo.findAll();
		List<UserRest> userList = new ArrayList<>();
		for (UserDetails i :users) {
			UserRest returnValue = convertDBtoFrontEnd(i);
			userList.add(returnValue);
		}
		
		return new ResponseEntity<List<UserRest>>(userList, HttpStatus.OK);
	}
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUsers(@PathVariable String userId) {
		  Optional<UserDetails> userDetails = userDetailsRepo.findById(userId);
		  UserRest returnValue = convertDBtoFrontEnd(userDetails.get());
		  return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	private UserRest convertDBtoFrontEnd(UserDetails userDetails) {
		  UserRest returnValue = new UserRest();
		  returnValue.set_id(userDetails.get_id());
		  returnValue.setEmail(userDetails.getEmail());
		  returnValue.setFirstName(userDetails.getFirstName());
		  returnValue.setLastName(userDetails.getLastName());
		  returnValue.setPassword(userDetails.getPassword());
		  return returnValue;
	}
	
		
//	//get particular user by unique id
//	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<UserRest> getUser(@PathVariable String parul) { 
//		return null;
//		userDetailsRepo.findAll();
//
//		/*
//		 * if (true) throw new
//		 * UserServiceException("A user service exception is thrown"); String firstName
//		 * = null; int firstNameLength = firstName.length();
//		 */
//
//		/*
//		 * if (users.containsKey(userId)) { return new
//		 * ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK); } else { return
//		 * new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT); }
//		 */
//		/*
//		 * UserRest returnValue = new UserRest(); 
//		 * returnValue.setEmail("test@test.com");
//		 * returnValue.setFirstName("Parul"); 
//		 * returnValue.setLastName("Sharma"); return
//		 * new ResponseEntity<UserRest>(HttpStatus.OK);
//		 */
//	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails) {
		userDetailsRepo.save(new UserDetails(userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getLastName() ));
		UserRest returnValue = convertDBtoFrontEnd(userDetails);
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		 
	}
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
	  MediaType.APPLICATION_JSON_VALUE }, produces = { 
	  MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }) 
	  public ResponseEntity<UserRest> updateUser(@PathVariable String id, @RequestBody UpdateUserDetailsRequestModel newUserDetails) { 
		  Optional<UserDetails> user = userDetailsRepo.findById(id);
		  System.out.println("Inside put function");
		  UserDetails oldUserDetail = user.get();
		  if (newUserDetails.getFirstName() != null) {
			  oldUserDetail.setFirstName(newUserDetails.getFirstName());
		  }
		  if (newUserDetails.getLastName() != null) {
			  oldUserDetail.setLastName(newUserDetails.getLastName());
		  }
		  if (newUserDetails.getEmail() != null) {
			  oldUserDetail.setEmail(newUserDetails.getEmail());
		  }
		  if (newUserDetails.getPassword() != null) {
			  oldUserDetail.setPassword(newUserDetails.getPassword());
		  }
		  userDetailsRepo.save(oldUserDetail);
		  UserRest returnValue = convertDBtoFrontEnd(oldUserDetail);		  
		  return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	} 
	 
	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable String id) {
		userDetailsRepo.deleteById(id);
	}

//	@RestController
//	@RequestMapping("parul") // http://localhost:8080/parul
//	public class ParulController {
//		@GetMapping
//		public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
//				@RequestParam(value = "limit", defaultValue = "1") int limit,
//				@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
//			return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
//		}
//
//		@GetMapping(path = "/{userId}")
//		public String getUser(@PathVariable String userId) {
//			return "get users was called with userId = " + userId;
//		}
//
//		@PostMapping
//		public String createUser() {
//			return "create user was called";
//		}
//
//		@PutMapping
//		public String updateUser() {
//			return "update user was called";
//		}
//
//		@DeleteMapping(path = "/{id}")
//		public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//			users.remove(id);
//			return ResponseEntity.noContent().build();
//		}
//	}
}
