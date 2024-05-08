package com.dpk.HospitalManagementSystem.service;

import java.util.List;

import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.User;

public interface AppointmentService {

	Appointment addAppointment(Appointment appointment);

// jun User login xa usko matra appointments chahiyo(nikalna), euta User ko multiple appointments huna sakxa[i.e euta user le multiple appointment save garna sakxa] tesaile List use gareko method ko return type
	List<Appointment> getAllAppointmentByLoginUser(User user);
	// == method name yo(j) lekhda ni huxa
	// List<Appointment> getAllAppointmentByIndividualUser(User user);
	// List<Appointment> getAppointmentsByUser(User user);

	// jun Doctor login xa usko matra appointments chahiyo(nikalna), euta Doctor ko
	// multiple appointments huna sakxa, tesaile List use gareko method ko return
	// type
	List<Appointment> getAllAppointmentByLoginDoctor(DoctorDetails doctor);
	// == method name yo(j) lekhda ni huxa
	// List<Appointment> getAllAppointmentByIndividualDoctor(DoctorDetails doctor);
	// List<Appointment> getAppointmentsByDoctor(DoctorDetails doctor);

	int getTotalAppointmentByLoginDoctor(DoctorDetails doctor);

	Appointment getAppointmentById(int id);

	Appointment updateAppointment(Appointment appointment);

	List<Appointment> getAllAppointments();

	int getTotalAppointments();

}
