package com.dpk.HospitalManagementSystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.model.UserDetails;
import com.dpk.HospitalManagementSystem.service.AppointmentService;
import com.dpk.HospitalManagementSystem.service.DoctorDetailsService;
import com.dpk.HospitalManagementSystem.service.UserDetailsService;
import com.dpk.HospitalManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DoctorDetailsService dDService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@ModelAttribute
	public User commonUser(Principal p, Model model) {

		String email = p.getName(); // j bata login garyo tyo aauxa

		// yo email baata user find garne
		User usr = userService.getUserByEmail(email);

		model.addAttribute("u", usr); // ("key", value)

		model.addAttribute("userRole", usr.getRole());

		return usr;

	}

	@GetMapping("/appointment")
	public String getUserAppointment(Model model, Principal p) {

		// ====== correct code here[find login user, then from this user find user

		List<DoctorDetails> doctorList = dDService.getAllDoctor();
		model.addAttribute("dList", doctorList); // ("key", value)

		// model.addAttribute("dList", dDService.getAllDoctor()); //("key", value)

		User user = commonUser(p, model);
		model.addAttribute("u", user); // ("key, value)

		return "UserAppointment";
	}

	@PostMapping("/addAppointment")
	public String addUserAppointment(@ModelAttribute Appointment appointment, HttpSession session) {

		Appointment appoint = appointmentService.addAppointment(appointment);
		if (appoint != null) {

			session.setAttribute("msg", "Successfully submitted"); // ("key", value)
			// return "redirect:/user/appointment";
		} else {
			session.setAttribute("msg", "Something went wrong on server"); // ("key", value)
			// return "redirect:/user/appointment";
		}

		return "redirect:/user/appointment";
	}

	@GetMapping("/viewAppointment")
	public String getViewAppointment(Principal p, Model model) {

		// find login user
		User user = commonUser(p, model);

		List<Appointment> appointmentList = appointmentService.getAllAppointmentByLoginUser(user);
		model.addAttribute("aList", appointmentList); // ("key", value)

		// model.addAttribute("aList",
		// appointmentService.getAllAppointmentByLoginUser(user)); //("key", value)

		return "ViewAppointment";
	}

	@GetMapping("/profile")
	public String getUserProfile(Principal p, Model model) {

		//find login user[user ho because ROLE_USER ]
		User user = commonUser(p, model);
		
		System.out.println("=============="+user+"=================");
		
		//yo login user baata user[UsetDetails waala user] find garne[user vaye UserDetails[user] return aauxa]
		UserDetails usr = userDetailsService.getUserByLoginUser(user);
		
		System.out.println("=============="+usr+"=================");
		
		//sidai yo usr model ma lagda ni hunxa, but ma yeha yo user ko id diyera feri yehi usr find garxu ani tyo aayeko user(u) lai lagxu
		//[Note: direct usr lada ni hunxa]
		UserDetails u = userDetailsService.getUserById(usr.getId());
		model.addAttribute("usr", u);	//("key", value)
		
		System.out.println("=============="+u+"=================");
		
		return "UserProfile";
	}
	
	@GetMapping("/editProfile")
	public String editUserProfile(@RequestParam int id, Model model) {
		
		UserDetails user = userDetailsService.getUserById(id);
		model.addAttribute("userModel", user);		//("key", value)
		//Here, userModel = userObject (j) lekhda ni  hunxa because UserDetails ko object aauxa
		
		//model.addAttribute("userModel", userDetailsService.getUserById(id));	
		
		return "UserProfileEdit";
	}
	
	@PostMapping("/updateProfile")
	public String updateUserProfile(@ModelAttribute UserDetails user, HttpSession session) {
		
		UserDetails usr = userDetailsService.updateUserProfile(user);
		
		if(usr != null) {
			
			session.setAttribute("msg", "Update Successfully");
			//return "redirect:/user/profile";
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");
			//return "redirect:/user/profile";
		}
		
		return "redirect:/user/profile";
	}

}
