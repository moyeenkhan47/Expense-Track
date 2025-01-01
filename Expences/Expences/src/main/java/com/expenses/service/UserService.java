package com.expenses.service;

import com.expenses.entity.User;
import com.expenses.model.RegistrationRequest;

public interface UserService {
	   public User registerUser(RegistrationRequest request);
}
