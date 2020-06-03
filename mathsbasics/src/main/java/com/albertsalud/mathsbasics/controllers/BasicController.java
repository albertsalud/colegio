package com.albertsalud.mathsbasics.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BasicController {

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld() {

		return "index";
	}
	
	@RequestMapping(value="/sumas")
	public String sums() {
		
		return "sumas";
		
	}

	@RequestMapping(value="/tablas")
	public String tables() {
		return "tablas";
		
	}
	
	@RequestMapping(value="/multiplicaciones")
	public String multiplications() {
		return "multiplicaciones";
		
	}
	
	@RequestMapping(value="/divisiones")
	public String divisions() {
		return "divisiones";
		
	}
	
	@RequestMapping(value="/restas")
	public String remainders() {
		return "restas";
		
	}
}

