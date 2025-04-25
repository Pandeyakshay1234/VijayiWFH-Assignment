package com.taskmanagement.services;

import java.util.List;

import com.taskmanagement.dto.UserDto;
import com.taskmanagement.entity.User;

public interface UserService {
	
	User createUser(UserDto userDto);
	User updateUser(Long id , UserDto userDto);
	void deleteUser(Long id );
	User getUserById(Long id);
	
	List<User> getAllUsers();
	

}
