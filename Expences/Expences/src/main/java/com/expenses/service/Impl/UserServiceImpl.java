package com.expenses.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expenses.entity.User;
import com.expenses.model.RegistrationRequest;
import com.expenses.repository.UserRepository;
import com.expenses.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	   @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public User registerUser(RegistrationRequest request) {
	        // Check if user already exists
	        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
	            throw new IllegalArgumentException("Username already taken");
	        }

	        // Create new user
	        User newUser = new User();
	        newUser.setUsername(request.getUsername());
	        newUser.setPassword(passwordEncoder.encode(request.getPassword())); 
	        newUser.setEmail(request.getEmail());
	        newUser.setFullName(request.getFullName());// Hash the password
	        

	        return userRepository.save(newUser);
	    }

}
