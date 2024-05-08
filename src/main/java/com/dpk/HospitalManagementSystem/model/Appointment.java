package com.dpk.HospitalManagementSystem.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment_tbl")
public class Appointment {
	
	@Id															//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)			//AI[auto]
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	private String fullName;
	
	private String age;
	
	private String gender;
	
	@DateTimeFormat(iso = ISO.DATE)			//YYYY-MM-DD format
	private LocalDate appointmentDate;
	
	private String email;
	
	private String mobileNumber;
	
	private String diseases;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private DoctorDetails doctor;

	
	private String address;
	
	private String status;
	
	
}
