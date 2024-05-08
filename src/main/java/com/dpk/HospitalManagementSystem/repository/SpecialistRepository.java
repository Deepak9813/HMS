package com.dpk.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.HospitalManagementSystem.model.Specialist;

@Repository			//This notation is optional
public interface SpecialistRepository extends JpaRepository<Specialist, Integer>{

	
}
