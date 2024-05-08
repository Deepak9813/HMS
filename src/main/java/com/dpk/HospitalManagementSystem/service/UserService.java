package com.dpk.HospitalManagementSystem.service;

import java.util.List;

import com.dpk.HospitalManagementSystem.model.User;

public interface UserService {

	User userSignup(User user); // User addUser(User user); yesari lekhda ni vayo method

	User adminSignup(User user);

	boolean deleteUser(Long id);

	User updateUser(User user);

	User getUserById(Long id);

	List<User> getAllUsers();

	User getUserByEmail(String email);
	

	void removeSessionMessage();
	
}
