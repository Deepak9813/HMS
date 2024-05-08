package com.dpk.HospitalManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.repository.SpecialistRepository;
import com.dpk.HospitalManagementSystem.service.SpecialistService;

@Service
public class SpecialistServiceImpl implements SpecialistService{
	
	@Autowired
	private SpecialistRepository specialistRepo;

	@Override
	public Specialist addSpecialist(Specialist specialist) {
		
		//return specialistRepo.save(specialist);
		
		Specialist newSpecialist = specialistRepo.save(specialist);
		return newSpecialist;
	}

	@Override
	public List<Specialist> getAllSpecialist() {
		
		return specialistRepo.findAll();
	}

	@Override
	public int getTotalSpecialists() {
		
		return specialistRepo.findAll().size();
	}

	@Override
	public Specialist getSpecialistById(int specialistId) {
		
		return specialistRepo.findById(specialistId).get();
	}

	
	
	

}
