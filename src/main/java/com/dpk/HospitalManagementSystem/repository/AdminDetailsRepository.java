package com.dpk.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.HospitalManagementSystem.model.AdminDetails;
import com.dpk.HospitalManagementSystem.model.User;

@Repository				//this annotation is optional
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Integer>{

	AdminDetails findByUser(User user);

	
}
