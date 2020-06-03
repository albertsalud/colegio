package com.albertsalud.mathsbasics.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/divisiones")
public class DivisionController {
	
	private int divisionLevel;
	
	@RequestMapping(value="/setLevel")
	public String setLevel(HttpServletRequest request) {
		divisionLevel = Integer.parseInt(request.getParameter("divisionLevel"));
		return "redirect:/divisiones/getDivision";
	}
	
	@RequestMapping(value="/getDivision")
	public String getMultiplication(HttpServletRequest request) {
		request.setAttribute("divisionLevel", divisionLevel);
		
		request.setAttribute("num1", generateRandomNumber());
		request.setAttribute("num2", divisionLevel);
		
		return "divisiones";
	}

	private Object generateRandomNumber() {
		return new Random().nextInt(989) + 10;
	}
	
	@RequestMapping(value="/validate")
	public String validate(HttpServletRequest request) {
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			int result = Integer.parseInt(request.getParameter("result"));
			int mod = Integer.parseInt(request.getParameter("mod"));
		
			request.setAttribute("status", num1 / num2 == result && num1 % num2 == mod);
			
		} catch (Exception e ) {
			request.setAttribute("status", false);
		}
		
		return this.getMultiplication(request);
		
	}

}
