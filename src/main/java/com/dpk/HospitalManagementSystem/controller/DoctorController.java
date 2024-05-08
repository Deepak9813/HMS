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

import com.dpk.HospitalManagementSystem.model.Appointment;
import com.dpk.HospitalManagementSystem.model.DoctorDetails;
import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.service.AppointmentService;
import com.dpk.HospitalManagementSystem.service.DoctorDetailsService;
import com.dpk.HospitalManagementSystem.service.SpecialistService;
import com.dpk.HospitalManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorDetailsService dDService;
	
	@Autowired
	private SpecialistService specialistService;

	@ModelAttribute
	public User commonUser(Principal p, Model model) {

		String email = p.getName(); // j bata login garyo tyo aauxa

		// yo email baata user find garne
		User usr = userService.getUserByEmail(email);

		model.addAttribute("u", usr); // ("key", value)

		model.addAttribute("userRole", usr.getRole());

		return usr;

	}

	@GetMapping("/dashboard")
	public String getAdminDashboard(Principal p, Model model) {

		// find login user[doctor ho because ROLE_DOCTOR]
		User user = commonUser(p, model);

		// System.out.println("=============================="+user+"========================");

		// yo login user bata doctor find garne[doctor vaye DoctorDetails[doctor] return
		// aauxa]
		DoctorDetails doctor = dDService.getDoctorByLoginUser(user);

		// System.out.println("=============================="+doctor+"========================");

		int totalDoctors = dDService.getTotalDoctors();
		model.addAttribute("TotalDoctors", totalDoctors); // ("key", value)

		int totalAppointmentByLoginDoctor = appointmentService.getTotalAppointmentByLoginDoctor(doctor);
		model.addAttribute("TotalAppointmentByLoginDoctor", totalAppointmentByLoginDoctor); // ("key", value)

		return "DoctorDashboard";
	}

	@GetMapping("/viewAppointment")
	public String getViewAppointment(Principal p, Model model) {

		// find login user[doctor ho because ROLE_DOCTOR]
		User user = commonUser(p, model);

		// System.out.println("=============================="+user+"========================");

		// yo login user bata doctor find garne[doctor vaye DoctorDetails[doctor] return
		// aauxa]
		DoctorDetails doctor = dDService.getDoctorByLoginUser(user);

		// System.out.println("=============================="+doctor+"========================");

		List<Appointment> appointmentList = appointmentService.getAllAppointmentByLoginDoctor(doctor);
		model.addAttribute("aList", appointmentList); // ("key", value)

		// model.addAttribute("aList",
		// appointmentService.getAllAppointmentByLoginUser(user)); //("key", value)

		return "Patient";
	}

	// comment => edit waala garna khojeko ho
	@GetMapping("/comment/{id}")
	public String getComment(@PathVariable int id, Model model) {

		Appointment appointment = appointmentService.getAppointmentById(id);
		model.addAttribute("appointmentModel", appointment); // ("key", value)
		// Here, appointmentModel = appointmentObject (j) lekhda ni hunxa because
		// Appointment ko object aauxa

		// model.addAttribute("appointmentModel",
		// appointmentService.getAppointmentById(id)); //("key", value)

		return "Comment";
	}

	@PostMapping("/updateStatus")
	public String updateCommentStatus(@ModelAttribute Appointment appointment, @RequestParam String comment,
			HttpSession session) {

		appointment.setStatus(comment);

		Appointment appoint = appointmentService.updateAppointment(appointment);

		if (appoint != null) {

			// session.setAttribute("msg", "Comment updated successfully"); //("key", value)
			session.setAttribute("msg", "Status updated successfully"); // ("key", value)
			// return "redirect:/doctor/viewAppointment";
		} else {
			session.setAttribute("msg", "Something went wrong on server"); // ("key", value)
			// return "redirect:/doctor/viewAppointment";
		}

		return "redirect:/doctor/viewAppointment";
	}

	@GetMapping("/profile")
	public String getDoctorProfile(Principal p, Model model) {

		// find login user[doctor ho because ROLE_DOCTOR]
		User user = commonUser(p, model);

		System.out.println("=================" + user + "====================");

		// yo login user bata doctor find garne[doctor vaye DoctorDetails[doctor] return
		// aauxa]
		DoctorDetails doctor = dDService.getDoctorByLoginUser(user);

		System.out.println("=================" + doctor + "====================");

		//sidai yo doctor model ma lagda ni hunxa, but ma yeha yo doctor ko id diyera feri yehi doctor(doct) find garxu ani tyo aayeko doctor(doct) lai lagxu
		//[Note: direct doctor lada ni hunxa]
		DoctorDetails doct = dDService.getDoctorById(doctor.getId());
		model.addAttribute("doc", doct); // ("key", value)
		// doct = doctor ho name same hunxa tesaile doc matra lekheko
		// Here, doctorModel = doctorObject (j) lekhda ni hunxa because Doctor ko object
		// aauxa

		System.out.println("=================" + doct + "====================");

		Specialist specialist = specialistService.getSpecialistById(doct.getSpecialist().getId());
		model.addAttribute("spec", specialist); // ("key", value)
		
		System.out.println("========="+specialist+"================");

		// model.addAttribute("spec", specialistService.getSpecialistById(doct.getSpecialist().getId()); //("key",
		// value)

		return "DoctorProfile";
	}

	@GetMapping("/editProfile")
	public String editDoctorProfile(@RequestParam int id, Model model) {

		DoctorDetails doctor = dDService.getDoctorById(id);
		model.addAttribute("doctorModel", doctor); // ("key", value)
		// Here, doctorModel = doctorObject (j) lekhda ni hunxa because Doctor ko object
		// aauxa

		List<Specialist> specList = specialistService.getAllSpecialist();
		model.addAttribute("sList", specList); // ("key", value)

		// model.addAttribute("sList", specialistService.getAllSpecialist()); //("key",
		// value)

		return "DoctorProfileEdit";
	}

	@PostMapping("/updateProfile")
	public String updateDoctorProfile(@ModelAttribute DoctorDetails doctor, HttpSession session) {

		
		DoctorDetails doct = dDService.updateDoctorProfile(doctor);

		if (doct != null) {

			session.setAttribute("msg", "Profile Updated Successfully"); // ("key", value)
			// return "redirect:/doctor/profile";
		} else {
			session.setAttribute("msg", "Something went wrong on server"); // ("key", value)
			// return "redirect:/doctor/profile";
		}

		return "redirect:/doctor/profile";
	}

}
