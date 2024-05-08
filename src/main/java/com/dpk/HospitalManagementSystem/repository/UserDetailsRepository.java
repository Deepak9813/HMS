package com.dpk.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.model.UserDetails;

@Repository 	//this annotation is optional
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	UserDetails findByUser(User user);

	
}
