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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_details_tbl") 
public class UserDetails {

	@Id													//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//AI[auto]
	private int id;
	
	private String fullName;
		
	private String mobileNumber;
	
	@DateTimeFormat(iso = ISO.DATE)			//YYYY-MM-DD format 
	private LocalDate dob;
	
	private String gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	// Note @oneToMany relationship ma column create hudaina , so nalekheko @joincolumn,
	// column yo jun class(or table) ma map vaaxa[i.e appointment_tbl(Appointment)]
	// ma column create hunxa
}
