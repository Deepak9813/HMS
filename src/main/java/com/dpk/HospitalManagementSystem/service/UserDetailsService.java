package com.dpk.HospitalManagementSystem.service;

import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.model.UserDetails;

public interface UserDetailsService {

	UserDetails userSignup(UserDetails userDetails);
	// UserDetails addUser(UserDetails userDetails);

	// ====== login user bata doctor find garna =========
	UserDetails getUserByLoginUser(User user);

	int getTotalUsers();

	UserDetails getUserById(int id);

	UserDetails updateUserProfile(UserDetails user);

}
