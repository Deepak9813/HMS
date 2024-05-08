package com.dpk.HospitalManagementSystem.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor_details_tbl") 
public class DoctorDetails {
	
	@Id													//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//AI[auto]
	private int id;
	
	private String fullName;
		
	private String mobileNumber;
	
	@DateTimeFormat(iso = ISO.DATE)			//YYYY-MM-DD format 
	private LocalDate dob;
	
	private String gender;
	
	private String qualification;
	
	
	@ManyToOne 			//eg. Deepak, Ramesh, Raju, etc. eutai(eg. Surgen) specialist huna sakxa
	@JoinColumn(name = "specialist_id")
	private Specialist specialist;
	
	private String experienceYear;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

	
}
