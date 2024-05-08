package com.dpk.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.HospitalManagementSystem.model.User;

@Repository			//this notation is optional
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	

}
