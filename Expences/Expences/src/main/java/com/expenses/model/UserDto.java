package com.expenses.model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserDto {
	
	private Long userId;
	private String username;
	private String password;
	private String email;
	private String fullName;
}
