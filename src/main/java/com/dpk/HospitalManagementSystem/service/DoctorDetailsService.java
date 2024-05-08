package com.dpk.HospitalManagementSystem.service;

import java.util.List;

import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.model.User;

public interface DoctorDetailsService {

	DoctorDetails addDoctor(DoctorDetails doctor);

	boolean deleteDoctor(int id);

	DoctorDetails updateDoctor(DoctorDetails doctor);

	DoctorDetails getDoctorById(int id);

	List<DoctorDetails> getAllDoctor();

	// ====== login user bata doctor find garna =========
	DoctorDetails getDoctorByLoginUser(User user);

	int getTotalDoctors();


	DoctorDetails updateDoctorProfile(DoctorDetails doctor);

}
