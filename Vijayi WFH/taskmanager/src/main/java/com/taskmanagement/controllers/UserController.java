package com.taskmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.dto.UserDto;
import com.taskmanagement.entity.User;
import com.taskmanagement.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")

public class UserController {
	
	@Autowired
	private   UserService userService;
	
	@PostMapping("/create")
	
	public   ResponseEntity<User> createUser( @Valid @RequestBody UserDto dto) {
		
		 User created = userService.createUser(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
		
	}
	
	@PutMapping("/update/{id}")
	
	public User updateuser(@PathVariable Long id , @RequestBody UserDto userDto) {
		
		return userService.updateUser(id, userDto);
	}
	
	@DeleteMapping("/delete/{id}")
	
	public void deleteUser(@PathVariable Long id) {
		
		userService.deleteUser(id);
	}
	
	 @GetMapping("/getUser/{id}")
	 
	  public User getUser(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }

	  @GetMapping
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
	

}
