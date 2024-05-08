package com.dpk.HospitalManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tbl")
public class User{
	
	@Id																//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)				//AI[auto]
	private Long id;
	
	@Column(nullable = false, unique =  true)			//optional
	private String email;
	
	@Column(nullable = false)		//optional
	private String password;
	
	@Column(nullable = false)						//optional[hami save garnu vanda agaadi nai set garxau internally]
	private String role;
	
	// Note: front end baata input ma required rakhye null aaudaina and unique backend code bata  garna  yo @Column(nullable = false, unique = true), @Column(nullable = false) nalekhda ni huxa

	

}
