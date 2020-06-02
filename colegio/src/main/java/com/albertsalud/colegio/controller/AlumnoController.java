package com.albertsalud.colegio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.service.AlumnoServices;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoServices alumnoServices;
	
	@GetMapping(value = {"", "/"})
	public String listEvaluaciones(@AuthenticationPrincipal UserDetails user,
			ModelMap model) {
		Alumno alumno = (Alumno) user;
		
		model.addAttribute("alumno", alumnoServices.getAlumno(alumno.getId()));
		
		return "alumno";
	}
}
