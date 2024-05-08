package com.dpk.HospitalManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dpk.HospitalManagementSystem.model.Specialist;
import com.dpk.HospitalManagementSystem.service.SpecialistService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SpecialistController {

	@Autowired
	private SpecialistService specialistService;

	// @PostMapping("/addSpecialist") // we can use any url name but that must match
	// with form action
	@PostMapping("/saveSpecialist")
	public String postUserSignup(@ModelAttribute Specialist specialist, HttpSession session) {

		Specialist spec = specialistService.addSpecialist(specialist);

		if (spec != null) {

			// session.setAttribute("msg", "Specialist Save Successfully");
			session.setAttribute("msg", "Specialist Added Successfully"); // ("key", value)
			// return "redirect:/admin/dashboard";
		} else {

			session.setAttribute("msg", "Something went wrong on server"); // ("key", value)
			// return "redirect:/admin/dashboard";
		}

		return "redirect:/admin/dashboard";
	}

}
