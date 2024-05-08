package com.dpk.HospitalManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "specialist_tbl")
public class Specialist {
	
	@Id												//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//AI[auto]
	private int id;
	
	private String specialistName;

}
