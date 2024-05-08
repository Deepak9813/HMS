package com.dpk.HospitalManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.repository.UserRepository;

@Component
//@Service   //we can also write this @Service annotation 
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(email);

		if (user == null) {

			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		// we can also write directly without writing else block
		else {

			return new CustomUserDetails(user);
		}

	}

}
