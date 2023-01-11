package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.model.User;
import com.wallet.service.UserService;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/create-user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@PutMapping(path = "/update-user")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@PatchMapping(path = "/partial-update-user")
	public ResponseEntity<?> partialUpdateUser(@RequestBody User user){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.partialUpdateUser(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<?> getAllUsers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/user/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

}
