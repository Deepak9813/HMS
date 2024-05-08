package com.dpk.HospitalManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.repository.AppointmentRepository;
import com.dpk.HospitalManagementSystem.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Override
	public Appointment addAppointment(Appointment appointment) {

		// === user le appointment lida first ma Pending rakhne status [backend baata
		// set
		// gareko maile staus] =====
		appointment.setStatus("Pending");

		// ==== frontend baata garda ni hunthyo "Pending"
		// yesari[<input type="hidden" name="status" value="Pending">] ======

		// return appointmentRepo.save(appointment);

		Appointment newAppointment = appointmentRepo.save(appointment);
		return newAppointment;
	}

	@Override
	public List<Appointment> getAllAppointmentByLoginUser(User user) {

		return appointmentRepo.findByUser(user);
	}

	@Override
	public List<Appointment> getAllAppointmentByLoginDoctor(DoctorDetails doctor) {

		return appointmentRepo.findByDoctor(doctor);
	}
	
	@Override
	public int getTotalAppointmentByLoginDoctor(DoctorDetails doctor) {
		
		return appointmentRepo.findByDoctor(doctor).size();
	}

	@Override
	public Appointment getAppointmentById(int id) {

		return appointmentRepo.findById(id).get();
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {

		// return appointmentRepo.save(appointment);

		Appointment updateAppointment = appointmentRepo.save(appointment);
		return updateAppointment;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		
		return appointmentRepo.findAll();
	}

	@Override
	public int getTotalAppointments() {
		
		return appointmentRepo.findAll().size();
	}

	

}
