package com.dpk.HospitalManagementSystem.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admin_details_tbl")
public class AdminDetails {
	
	@Id													//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//AI[auto]
	private int id;
	
	private String fullName;
	
	private String mobileNumber;
	
	@DateTimeFormat(iso = ISO.DATE)		//YYY-MM-DD format
	private LocalDate dob;
	
	private String gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	
	
}
