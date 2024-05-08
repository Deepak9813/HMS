package com.dpk.HospitalManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.model.UserDetails;
import com.dpk.HospitalManagementSystem.repository.UserDetailsRepository;
import com.dpk.HospitalManagementSystem.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails userSignup(UserDetails userDetails) {

		// ============ Encrypt Password ======================
		userDetails.getUser().setPassword(passwordEncoder.encode(userDetails.getUser().getPassword()));

		// =========== Set Role [Set Role of User] ============
		//user.setRole("ROLE_USER");
		userDetails.getUser().setRole("ROLE_USER");

		// return userDetailsRepo.save(userDetails);

		UserDetails saveUserDetails = userDetailsRepo.save(userDetails);
		return saveUserDetails;
		
	}


	@Override
	public UserDetails getUserByLoginUser(User user) {
		
		return userDetailsRepo.findByUser(user);
	}


	@Override
	public int getTotalUsers() {
		
		return userDetailsRepo.findAll().size();
	}

	@Override
	public UserDetails getUserById(int id) {
		
		return userDetailsRepo.findById(id).get();
	}

	@Override
	public UserDetails updateUserProfile(UserDetails user) {
		
		//return userDetailsRepo.save(user);
		UserDetails updateUserProfile = userDetailsRepo.save(user);
		return updateUserProfile;
	}



}
