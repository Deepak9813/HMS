package com.dpk.HospitalManagementSystem.service;

import com.dpk.HospitalManagementSystem.model.AdminDetails;
import com.dpk.HospitalManagementSystem.model.User;

public interface AdminDetailsService {

	AdminDetails adminSignup(AdminDetails adminDetails);
	// AdminDetails addAdmin(AdminDetails adminDetails);
	

	// ===== login user baata admin find garna =============
	AdminDetails getAdminByLoginUser(User user);

	AdminDetails getAdminById(int id);

	AdminDetails updateAdminProfile(AdminDetails admin);

}
