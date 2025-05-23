package com.taskmanagement.serviceimplementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.dto.UserDto;
import com.taskmanagement.entity.User;
import com.taskmanagement.execptions.ResourceNotFoundException;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.services.UserService;


@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired	
	private UserRepository userRepo;

	@Override
	public User createUser(UserDto userDto) {
		
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		  return userRepo.save(user);
	}

	@Override
	public User updateUser(Long id, UserDto userDto) {
		
		User user = null;

		 try {
			user = userRepo.findById(id)
			            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 user.setFirstName(userDto.getFirstName());
	        user.setLastName(userDto.getLastName());
	        user.setTimezone(userDto.getTimezone());
	        user.setActive(userDto.isActive());
	        return userRepo.save(user);
	}

	
	@Override
	public void deleteUser(Long id) {

		User user = null;
		try {
			user = userRepo.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        userRepo.delete(user);
	}

	@Override
	public User getUserById(Long id) {
		
		User user = null;
		 try {
			user =  userRepo.findById(id)
			            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		} catch (ResourceNotFoundException e) {

			e.printStackTrace();
		}
		 
		 return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		List users = userRepo.findAll();
		return users;
	}

}
