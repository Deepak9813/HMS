package com.dpk.HospitalManagementSystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dpk.HospitalManagementSystem.model.AdminDetails;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.model.UserDetails;
import com.dpk.HospitalManagementSystem.service.AdminDetailsService;
import com.dpk.HospitalManagementSystem.service.UserDetailsService;
import com.dpk.HospitalManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AdminDetailsService adminDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String index() {

		return "Index";
	}

	@GetMapping("/signin")
	public String getLogin(@RequestParam(required = false) String admin, @RequestParam(required = false) String doctor,
			@RequestParam(required = false) String user, HttpSession session, Model model) {

		if (admin != null) {

			session.setAttribute("LoginName", "Admin Login");
		} else if (doctor != null) {

			session.setAttribute("LoginName", "Doctor Login");
		} else if (user != null) {

			session.setAttribute("LoginName", "User Login");
			model.addAttribute("url", "/userSignup");
		}

		return "Login";

	}

	@GetMapping("/userLogout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "Logout";
	}

	@GetMapping("/userSignup")
	public String getUserSignup() {

		return "UserSignup";
	}

	// @PostMapping("/userSignup") // we can use any url name but that must match
	// with form action
	@PostMapping("/saveUser")
	public String postUserSignup(@ModelAttribute UserDetails userDetails, HttpSession session) {

		// check the email(User) already exist or not
		User usr = userService.getUserByEmail(userDetails.getUser().getEmail());
		if (usr != null) {

			session.setAttribute("msg", "Email(User) already exist..!!");
			return "redirect:/userSignup";
		}

		// userDtls = user ho
		UserDetails userDtls = userDetailsService.userSignup(userDetails);

		if (userDtls != null) {

			session.setAttribute("msg", "Signup Successfully"); // ("key", value);
			// return "redirect:/userSignup";
		} else {
			session.setAttribute("msg", "Something went wrong on server"); // ("key", value);
			// return "redirect:/userSignup";
		}

		return "redirect:/userSignup";
	}

	@GetMapping("/adminSignup/DBCompany2055")
	public String getAdminSignup() {

		return "AdminSignup";
	}

	// @PostMapping("/adminSignup") // we can use any url name but that must match
	// with form action
	@PostMapping("/saveAdmin")
	public String postAdminSignup(@ModelAttribute AdminDetails adminDetails, HttpSession session) {

		// check the email(user/doctor) already exist or not
		User usr = userService.getUserByEmail(adminDetails.getUser().getEmail());
		if (usr != null) {

			session.setAttribute("msg", "Email(User) already exist..!!"); // ("key', value)
			// return "redirect:/adminSignup/DBCompany2055";
		}

		// adminDtls = admin ho
		AdminDetails adminDtls = adminDetailsService.adminSignup(adminDetails);

		if (adminDtls != null) {

			session.setAttribute("msg", "Signup Successfully"); // ("key', value)
			// return "redirect:/adminSignup/DBCompany2055";
		} else {

			session.setAttribute("msg", "Something went wrong on server"); // ("key', value)
			// return "redirect:/adminSignup/DBCompany2055";
		}

		return "redirect:/adminSignup/DBCompany2055";
	}

//	@GetMapping("/doctorSignup")
//	public String getDoctorSignup() {
//
//		return "DoctorSignup";
//	}

	@ModelAttribute
	public void commonUser(Principal p, Model model) {

		if (p != null) {

			String email = p.getName(); // j bata login garyo tyo aauxa

			// yo email baata user find garne
			User usr = userService.getUserByEmail(email);

			model.addAttribute("u", usr); // ("key", value)

			model.addAttribute("userRole", usr.getRole());

		}
	}

	// ============= for Forgot Password ====================
	@GetMapping("/forgotPassword")
	public String getForgotPasswordForm() {

		return "ForgotPasswordForm";
	}

	@PostMapping("/forgotPassword")
	public String postForgotPasswordForm(@ModelAttribute User user, Model model, HttpSession session) {

		User usr = userService.getUserByEmail(user.getEmail());

		if (usr != null) {

			// return "ChangePasswordForm";

			// =========== Password Change garda id lagera(id controller ma lagera ) jaane,
			// ani tyo id lai uta recive garne ==========

			return "redirect:/changePassword/" + usr.getId();
		}

		// else lekhera lekha ni hunxa yo talako code
		session.setAttribute("msg", "Invalid Email...!!");
		return "redirect:/forgotPassword";

	}

	// id receive garne ani usko(User ko) data lagera ChangePasswordForm ma jaane
	// [i.e page open huda data aaunu paryo]
	@GetMapping("/changePassword/{id}")
	public String getChangePasswordForm(@PathVariable Long id, Model model) {

		User user = userService.getUserById(id);

		model.addAttribute("userModel", user); // ("key", value)
		// Here, userModel = userObject (j) lekhda ni hunxa because User ko object aauxa

		// model.addAttribute("userModel", userService.getUserById(id)); //("key",
		// value)

		return "ChangePasswordForm";
	}

	@PostMapping("/changePassword")
	public String postChangePassword(@ModelAttribute User user, @RequestParam(required = false) String currentPassword, @RequestParam String newPassword,
			Principal p, Model model, HttpSession session) {

		// ==== If user is in Login Mode[If Login Mode occurs], Here we need to check
		// current database password and currentPassword from input field match or not
		// =====
		if (p != null) {
			
			String email = p.getName();
			
			//yo email baata user find garne
			User u = userService.getUserByEmail(email);
			
			String dbPassword = u.getPassword();
			
			boolean passwordMatches = passwordEncoder.matches(currentPassword, dbPassword);
			
			if(!passwordMatches) {	//false
				
				session.setAttribute("msg", "Current Password doesn't match");
				//return "redirect:/changePassword/" + u.getId();
				return "redirect:/changePassword/" + user.getId();
			}

		}
		
		user.setPassword(newPassword);
		
		User usr = userService.updateUser(user);
		if(usr != null) {
			
			session.setAttribute("msg", "Password Changed Successfully");	//("key", value)
			//return "redirect:/signin";
		}
		else {
			session.setAttribute("msg", "Something went wrong on server");		//("key", value)
			//return "redirect:/signin";
		}
		
		return "redirect:/signin";
		
	}
}
