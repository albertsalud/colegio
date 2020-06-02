package com.albertsalud.colegio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.albertsalud.colegio.dto.EvaluacionFormularioDTO;
import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Profesor;
import com.albertsalud.colegio.model.service.ProfesorServices;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired
	private ProfesorServices profesorServices;
	
	@GetMapping(value= {"", "/"})
	public String listClases(@AuthenticationPrincipal UserDetails user,
			ModelMap model) {
		
		Profesor profesor = (Profesor) user;
		
		setModelMapBasicAttributes(model, profesor);
		
		return "profesor";
		
	}

	private void setModelMapBasicAttributes(ModelMap model, Profesor profesor) {
		Profesor profesorToReturn = profesorServices.getProfesor(profesor.getId());
		
		model.addAttribute("profesor", profesorToReturn);
		model.addAttribute("cursos", getCursos(profesorToReturn.getClases()));
		model.addAttribute("asignaturas", getAsignaturas(profesorToReturn.getClases()));
		
	}

	private List<Asignatura> getAsignaturas(List<Clase> clases) {
		List<Asignatura> asignaturas = new ArrayList<>();
		
		clases.forEach(cl -> {
			if(!asignaturas.contains(cl.getAsignatura())) asignaturas.add(cl.getAsignatura());
		});
		
		return asignaturas;
	}

	private List<Curso> getCursos(List<Clase> clases) {
		List<Curso> cursos = new ArrayList<>();
		clases.forEach(cl -> {
			if(!cursos.contains(cl.getCurso())) cursos.add(cl.getCurso());
		});
		
		return cursos;
	}

	@RequestMapping(value= "/ver-evaluacion", method = {RequestMethod.GET, RequestMethod.POST})
	public String listEvaluaciones(
			@ModelAttribute(name = "evaluacionesClase") EvaluacionFormularioDTO dto,
			@AuthenticationPrincipal UserDetails user,
			ModelMap model
			) {
		Profesor profesor = (Profesor) user;
		
		setModelMapBasicAttributes(model, profesor);
		if(dto.getEvaluaciones() == null) {
			dto = EvaluacionFormularioDTO.builder()
								.asignatura(dto.getAsignatura())
								.curso(dto.getCurso())
								.evaluaciones(profesorServices.getEvaluaciones(dto.getCurso(), dto.getAsignatura()))
								.build();
			
			model.addAttribute("evaluacionesClase", dto);
		}
		
		return "profesor";
	}
	
	@PostMapping("/guardar-evaluaciones")
	public String saveEvaluaciones(@Valid @ModelAttribute(value = "evaluacionesClase") EvaluacionFormularioDTO evaluaciones,
			BindingResult bindResult,
			@AuthenticationPrincipal UserDetails user,
			ModelMap model
			) {
		
		if(bindResult.hasErrors()) {
			log.info("El formulario de evaluaciones tiene errores!");
			Profesor profesor = (Profesor) user;
			setModelMapBasicAttributes(model, profesor);
			
			return "profesor";
		}
		
		profesorServices.saveEvaluaciones(evaluaciones.getEvaluaciones());
		
		return "redirect:/profesor";
	}
}
