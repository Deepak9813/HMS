package com.dpk.HospitalManagementSystem.service;

import java.util.List;

import com.dpk.HospitalManagementSystem.model.Specialist;

public interface SpecialistService {

	Specialist addSpecialist(Specialist specialist);
	
	
	List<Specialist> getAllSpecialist();
	
	int getTotalSpecialists();
	

	Specialist getSpecialistById(int specialistId);
}
