package com.stackroute.newsapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exception.UserAlreadyExistsException;
import com.stackroute.newsapp.exception.UserNotFoundException;
import com.stackroute.newsapp.service.SecurityTokenGenerator;
import com.stackroute.newsapp.service.UserService;

/**
 * @author Adarsh
 *
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(path = "/api/userservice")
public class NewsAuthController {
	private UserService userservice;

	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	@Autowired
	public NewsAuthController(final UserService userservice) {
		this.userservice = userservice;
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/register", produces = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userservice.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}

	/**
	 * @param logindetail
	 * @return
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@RequestBody User logindetail) {
		try {
			String userId = logindetail.getUserid();
			String password = logindetail.getPassword();

			if (userId == null || password == null) {
				return new ResponseEntity<String>("username and password cant be empty", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			User user = null;
			try{
			user=userservice.findbyUserIdAndPassword(userId, password);
			}catch(UserNotFoundException e){
				return new ResponseEntity<String>("user with given Id doesnot exist", HttpStatus.NOT_FOUND);
			}
		
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}

	}

}
