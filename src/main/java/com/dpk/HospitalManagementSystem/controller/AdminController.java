package com.dpk.HospitalManagementSystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dpk.HospitalManagementSystem.model.AdminDetails;
import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.service.AdminDetailsService;
import com.dpk.HospitalManagementSystem.service.AppointmentService;
import com.dpk.HospitalManagementSystem.service.DoctorDetailsService;
import com.dpk.HospitalManagementSystem.service.SpecialistService;
import com.dpk.HospitalManagementSystem.service.UserDetailsService;
import com.dpk.HospitalManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SpecialistService specialistService;
	
	@Autowired
	private DoctorDetailsService dDService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AdminDetailsService adminDetailsService;
		
	
	@GetMapping("/dashboard")
	public String getAdminDashboard(Model model) {
		
		int totalSpecialists = specialistService.getTotalSpecialists();
		model.addAttribute("TotalSpecialist", totalSpecialists);	//("key", value)
		
		int totalAppointments = appointmentService.getTotalAppointments();
		model.addAttribute("TotalAppointments", totalAppointments);	//("key", value)
		
		int totalDoctors = dDService.getTotalDoctors();
		model.addAttribute("TotalDoctors", totalDoctors);	//("key", value)
		
		int totalUsers = userDetailsService.getTotalUsers();
		model.addAttribute("TotalUsers", totalUsers);	//("key", value)

		return "AdminDashboard";
	}

	
	@ModelAttribute
	public User commonUser(Principal p, Model model) {

		String email = p.getName(); // j bata login garyo tyo aauxa

		// yo email baata user find garne
		User usr = userService.getUserByEmail(email);

		model.addAttribute("u", usr); // ("key", value)

		model.addAttribute("userRole", usr.getRole());
		
		return usr;

	}

	
	@GetMapping("/addDoctor")
	public String getAddDoctor(Model model) {
		
		List<Specialist> specList = specialistService.getAllSpecialist();
		model.addAttribute("sList", specList);		//("key", value)
		
		//model.addAttribute("sList", specialistService.getAllSpecialist());		//("key", value)
		
		return "AddDoctor";
	}
	
	@PostMapping("/addDoctor")
	public String postAddDoctor(@ModelAttribute DoctorDetails doctor, @ModelAttribute User user, HttpSession session) {
		
		//check the email(doctor/user) already exist or not
		User usr = userService.getUserByEmail(doctor.getUser().getEmail());
		if(usr != null) {
			
			session.setAttribute("msg", "Email(User) already exist..!!");
			return "redirect:/admin/addDoctor";
		}
		
		
		DoctorDetails doc = dDService.addDoctor(doctor);
		if(doc != null) {
			
			session.setAttribute("msg", "Added Successfully");
			//return "redirect:/admin/addDoctor";
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
			//return "redirect:/admin/addDoctor";
		}
		
		
		return "redirect:/admin/addDoctor";
	}

	
	@GetMapping("/viewDoctors")
	public String viewAllDoctors(Model model) {
		
		List<DoctorDetails> doctorList = dDService.getAllDoctor();
		model.addAttribute("dList", doctorList);	//("key", value)
		
		//model.addAttribute("dList", dDService.getAllDoctor());
		
		
		return "ViewDoctorList";
	}
	
	@GetMapping("/deleteDoctor/{id}")
	public String deleteDoctor(@PathVariable int id, HttpSession session) {
			
		boolean f = dDService.deleteDoctor(id);
		
		if(f) { //true
			
			session.setAttribute("msg", "Delete Successfully");
			//return "redirect:/admin/viewDoctors";
		}
		else {	
			session.setAttribute("msg", "Delete Successfully");
			//return "redirect:/admin/viewDoctors";
		}
		
		return "redirect:/admin/viewDoctors";
	}
	
	
	@GetMapping("/editDoctor")
	public String editDoctor(@RequestParam int id, Model model) {
		
		DoctorDetails doctor = dDService.getDoctorById(id);
		model.addAttribute("doctorModel", doctor);				//("key", value)
		//here, doctorModel = doctorObject (j) lekhda ni hunxa because Doctor ko object aauxa
		
		//model.addAttribute("doctorModel", dDService.getDoctorById(id));		//("key", value)
		
		
		List<Specialist> specList = specialistService.getAllSpecialist();
		model.addAttribute("sList", specList);		//("key", value)
				
		//model.addAttribute("sList", specialistService.getAllSpecialist());
		
		return "EditDoctor";
	}
	
	@PostMapping("/updateDoctor")
	public String updateDoctor(@ModelAttribute DoctorDetails doctor, HttpSession session) {
		
		DoctorDetails doc = dDService.updateDoctor(doctor);
		if(doc != null) {
			
			session.setAttribute("msg", "Update Successfully");
			//return "redirect:/admin/viewDoctors";
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
			//return "redirect:/admin/viewDoctors";
		}
		
		return "redirect:/admin/viewDoctors";
		
	}
	
	@GetMapping("/allAppointment")
	public String getAllPatientAppointments(Model model) {
		
		List<Appointment> appointmentList = appointmentService.getAllAppointments();
		model.addAttribute("aList", appointmentList);		//("key", value)
		
		//model.addAttribute("aList", appointmentService.getAllAppointments()); //("key", value)
		
		return "PatientAll";
	}
	
	@GetMapping("/profile")
	public String getAdminProfile(Principal p, Model model) {

		// find login user[doctor ho because ROLE_ADMIN]
		User user = commonUser(p, model);

		System.out.println("=================" + user + "====================");

		// yo login user bata admin find garne[admin vaye AdminDetails[admin] return
		// aauxa]
		AdminDetails admin = adminDetailsService.getAdminByLoginUser(user);
		

		System.out.println("=================" + admin + "====================");

		//sidai yo admin model ma lagda ni hunxa, but ma yeha yo admin ko id diyera feri yehi admin(a) find garxu ani tyo aayeko admin(a) lai lagxu
		//[Note: direct doctor lada ni hunxa]
		AdminDetails adm = adminDetailsService.getAdminById(admin.getId());
		model.addAttribute("a", adm); // ("key", value)
		// a = admin ho name same hunxa tesaile a matra lekheko

		System.out.println("=================" + adm + "====================");

		return "AdminProfile";
	}
	
	@GetMapping("/editProfile")
	public String editAdminProfile(@RequestParam int id, Model model) {
		
		AdminDetails admin = adminDetailsService.getAdminById(id);
		model.addAttribute("adminModel", admin);		//("key", value)
		//Here, adminModel = adminObject (j) lekhda ni hunxa because Admin ko object aauxa
		
		//model.addAttribute("adminModel", adminDetailsService.getAdminById(id));
		
		return "AdminProfileEdit";
		
	}
	
	@PostMapping("/updateProfile")
	public String updateAdminProfile(@ModelAttribute AdminDetails admin, HttpSession session) {
		
		AdminDetails adm = adminDetailsService.updateAdminProfile(admin);
		
		if(adm != null) {
			
			session.setAttribute("msg", "Update Successfully"); //("key", value)
			//return "redirect:/admin/profile";
		}
		else {
			session.setAttribute("msg", "Update Successfully"); //("key", value)
			//return "redirect:/admin/profile";
		}
		
		return "redirect:/admin/profile";
	}
	
}
