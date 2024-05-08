package com.dpk.HospitalManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.repository.DoctorDetailsRepository;
import com.dpk.HospitalManagementSystem.service.DoctorDetailsService;

@Service
public class DoctorDetailsServiceImpl implements DoctorDetailsService {

	@Autowired
	private DoctorDetailsRepository doctorRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public DoctorDetails addDoctor(DoctorDetails doctor) {

		// ============ Encrypt Password ======================
		doctor.getUser().setPassword(passwordEncoder.encode(doctor.getUser().getPassword()));

		// =========== Set Role [Set Role of Doctor] ============
		// user.setRole("ROLE_DOCTOR");
		doctor.getUser().setRole("ROLE_DOCTOR");

		// return doctorRepo.save(doctor);

		DoctorDetails saveDoctor = doctorRepo.save(doctor);
		return saveDoctor;
	}

	@Override
	public boolean deleteDoctor(int id) {

		DoctorDetails doctor = doctorRepo.findById(id).get();

		if (doctor != null) {

			doctorRepo.delete(doctor);
			// doctorRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public DoctorDetails updateDoctor(DoctorDetails doctor) {

		// ============= Password and Role lai change huna diyeko xaina tesale yeta
		// password encrypt and role set nagareko,
		// yo change nahuna dina maile form mai hidden pathayeko xu password and role
		// lai,
		// [note, role lai uta hidden nagarye yeta role lai set garne(same as
		// addDoctor() method)====

		// return doctorRepo.save(doctor);

		DoctorDetails updateDoctor = doctorRepo.save(doctor);
		return updateDoctor;
	}

	@Override
	public DoctorDetails getDoctorById(int id) {

		return doctorRepo.findById(id).get();
	}

	@Override
	public List<DoctorDetails> getAllDoctor() {

		return doctorRepo.findAll();
	}

	@Override
	public DoctorDetails getDoctorByLoginUser(User user) {

		return doctorRepo.findByUser(user);
	}

	@Override
	public int getTotalDoctors() {

		return doctorRepo.findAll().size();
	}

	@Override
	public DoctorDetails updateDoctorProfile(DoctorDetails doctor) {

		
		// return doctorRepo.save(doctor);
		DoctorDetails updateDoctorProfile = doctorRepo.save(doctor);
		return updateDoctorProfile;

	}

}
