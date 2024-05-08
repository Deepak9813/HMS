package com.dpk.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.User;

@Repository 	//this annotation is optional
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Integer>{

	DoctorDetails findByUser(User user);

	
}
