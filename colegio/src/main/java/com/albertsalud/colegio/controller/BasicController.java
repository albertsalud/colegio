package com.albertsalud.colegio.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.albertsalud.colegio.model.entities.Rol;
import com.albertsalud.colegio.model.entities.Usuario;

@Controller
public class BasicController {
	
	@GetMapping(value = {"", "/"})
	public String goHome() {
		return "redirect:/home";
	}
	
	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}
	
	@GetMapping("/home")
	public String executeLogin(@AuthenticationPrincipal UserDetails user) {
		Usuario usuario = (Usuario) user;
		
		return redirectByRol(usuario.getRol());
		
	}

	private String redirectByRol(Rol rol) {
		if(rol == Rol.ALUMNO) return "redirect:/alumno";
		if(rol == Rol.DIRECTOR) return "redirect:/director";
		return "redirect:/profesor";
	}
	
	@GetMapping("/error")
	public String goError() {
		return "error";
	}
}
