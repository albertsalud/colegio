package com.albertsalud.colegio.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.albertsalud.colegio.dto.CursoFormularioDTO;
import com.albertsalud.colegio.dto.ProfesorFormularioDTO;
import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Director;
import com.albertsalud.colegio.model.entities.Profesor;
import com.albertsalud.colegio.model.service.AsignaturaServices;
import com.albertsalud.colegio.model.service.CursoServices;
import com.albertsalud.colegio.model.service.ProfesorServices;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/director")
public class DirectorController {
	
	@Autowired private AsignaturaServices asignaturaServices;
	@Autowired private ProfesorServices profesorServices;
	@Autowired private CursoServices cursoServices;
	@Autowired private ModelMapper modelMapper;
	
	@GetMapping(value= {"", "/"})
	public String directorHome(@AuthenticationPrincipal UserDetails principal,
			ModelMap model) {
		model.addAttribute("usuario", (Director) principal);
		
		return "director";
	}
	
	
	
	@GetMapping("/asignaturas")
	public String listAsignaturas(ModelMap map) {
		
		map.addAttribute("asignaturas", asignaturaServices.listAsignaturas());
		
		return "asignaturas_listado";
	}
	
	@GetMapping("/asignaturas/nueva")
	public String nuevaAsignatura(@ModelAttribute("asignaturaBO") Asignatura asignatura) {
		return "asignaturas_formulario";
	}
	
	@GetMapping("/asignaturas/{idAsignatura}")
	public String getAsignatura(
			@PathVariable(name = "idAsignatura") long idAsignatura,
			ModelMap map) {
		map.addAttribute("asignaturaBO", asignaturaServices.findById(idAsignatura));
		
		return "asignaturas_formulario";
	}
	
	@PostMapping("/asignaturas/guardar")
	public String saveAsignatura(@Valid @ModelAttribute("asignaturaBO") Asignatura asignatura,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			log.info("La asignatura contiene errores");
			return "asignaturas_formulario";
		}
		asignaturaServices.saveAsignatura(asignatura);
		
		return "redirect:/director/asignaturas";
	}
	
	@GetMapping("/profesores")
	public String listProfesores(ModelMap map) {
		map.addAttribute("profesores", profesorServices.listProfesores());
		
		return "profesores_listado";
	}
	
	@GetMapping("/profesores/nuevo")
	public String nuevoProfesor(@ModelAttribute("profesorBO") ProfesorFormularioDTO dto) {
		return "profesores_formulario";
	}
	
	@PostMapping("/profesores/guardar")
	public String saveProfesor(@Valid @ModelAttribute("profesorBO") ProfesorFormularioDTO profesor,
			BindingResult bindingResult) {
		
		if(!profesor.getContrasena().contentEquals(profesor.getContrasena2())) {
			bindingResult.rejectValue("contrasena2", null, "Este campo debe coincidir con el valor de contraseña introducido");
		}
		
		if(bindingResult.hasErrors()) return "profesores_formulario";
		
		Profesor profesorToSave = modelMapper.map(profesor, Profesor.class);
		profesorServices.save(profesorToSave);
		
		return "redirect:/director/profesores";
	}
	
	@GetMapping("/profesores/{idProfesor}")
	public String getProfesor(
			@PathVariable(name = "idProfesor") long idProfesor,
			ModelMap map) {
		Profesor p = profesorServices.getProfesor(idProfesor);
		log.info("contraseña: " + p.getContrasena());
		ProfesorFormularioDTO profesorToReturn = modelMapper.map(p, ProfesorFormularioDTO.class);
		log.info("contraseña a devolver: " + profesorToReturn.getContrasena());
		profesorToReturn.setContrasena2(p.getContrasena());
		
		map.addAttribute("profesorBO", profesorToReturn);
		
		return "profesores_formulario";
	}
	
	
	@GetMapping("/cursos")
	public String listCursos(ModelMap map) {
		map.addAttribute("cursos", cursoServices.listCursos());
		
		return "cursos_listado";
	}
	
	@GetMapping("/cursos/nuevo")
	public String nuevoCurso(ModelMap map) {
		map.addAttribute("cursoBO", new CursoFormularioDTO());
		
		map.addAttribute("asignaturas", asignaturaServices.listAsignaturas());
		map.addAttribute("profesores", profesorServices.listProfesores());
		
		return "cursos_formulario";
	}
	
	@PostMapping("/cursos/guardar")
	public String saveCurso(@Valid @ModelAttribute CursoFormularioDTO curso,
			BindingResult bindingResult,
			@RequestParam String[] idAsignatura,
			@RequestParam String[] idProfesor,
			ModelMap map) {
		if(bindingResult.hasErrors()) {
			map.addAttribute("asignaturas", asignaturaServices.listAsignaturas());
			map.addAttribute("profesores", profesorServices.listProfesores());
			return "cursos_formulario";
		}
		
		Curso cursoToSave = modelMapper.map(curso, Curso.class);
		cursoServices.setClases(cursoToSave, idAsignatura, idProfesor);
		cursoServices.saveCurso(cursoToSave);
		
		return "redirect:/director/cursos";
	}
	
	@GetMapping("/cursos/{idCurso}")
	public String getCurso(
			@PathVariable(name = "idCurso") long idCurso,
			ModelMap map) {
		map.addAttribute("cursoBO", cursoServices.getCurso(idCurso));
		
		map.addAttribute("asignaturas", asignaturaServices.listAsignaturas());
		map.addAttribute("profesores", profesorServices.listProfesores());
		
		return "cursos_formulario";
	}

}
