package com.dpk.HospitalManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dpk.HospitalManagementSystem.model.AdminDetails;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.repository.AdminDetailsRepository;
import com.dpk.HospitalManagementSystem.service.AdminDetailsService;

@Service
public class AdminDetailsServiceImpl implements AdminDetailsService{
	
	@Autowired
	private AdminDetailsRepository adminDetailsRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public AdminDetails adminSignup(AdminDetails adminDetails) {
		
		// ============ Encrypt Password =================
		adminDetails.getUser().setPassword(passwordEncoder.encode(adminDetails.getUser().getPassword()));
		
		// ============ Set Role [Set Role of Admin] ==================
		//user.setRole("ROLE_ADMIN"); 
		adminDetails.getUser().setRole("ROLE_ADMIN");
		
		//return adminDetailsRepo.save(adminDetails);
		
		AdminDetails saveAdminDetails = adminDetailsRepo.save(adminDetails);
		return saveAdminDetails;
		
	}

	@Override
	public AdminDetails getAdminByLoginUser(User user) {
		
		return adminDetailsRepo.findByUser(user);
	}

	@Override
	public AdminDetails getAdminById(int id) {
		
		return adminDetailsRepo.findById(id).get();
	}

	@Override
	public AdminDetails updateAdminProfile(AdminDetails admin) {
		
		//return adminDetailsRepo.save(admin);
		AdminDetails updatedAdmin = adminDetailsRepo.save(admin);
		return updatedAdmin;
	}

	
}
