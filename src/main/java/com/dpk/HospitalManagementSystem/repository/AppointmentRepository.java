package com.dpk.HospitalManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	List<Appointment> findByUser(User user);

	List<Appointment> findByDoctor(DoctorDetails doctor);
	

}
