package com.albertsalud.mathsbasics.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/multiplicaciones")
public class MultiplicationController {
	
	private int multiplicationLevel;
	private int multiplicationTable;
	@RequestMapping(value="/setLevel")
	public String setLevel(HttpServletRequest request) {
		multiplicationLevel = Integer.parseInt(request.getParameter("multiplicationLevel"));
		multiplicationTable = Integer.parseInt(request.getParameter("multiplicationTable"));
		
		return "redirect:/multiplicaciones/getMultiplication";
	}
	
	@RequestMapping(value="/getMultiplication")
	public String getMultiplication(HttpServletRequest request) {
		request.setAttribute("multiplicationLevel", multiplicationLevel);
		request.setAttribute("multiplicationTable", multiplicationTable);
		
		request.setAttribute("num1", generateRandomNumber());
		request.setAttribute("num2", multiplicationTable);
		
		return "multiplicaciones";
	}

	private Object generateRandomNumber() {
		return new Random().nextInt((int) Math.pow(10, multiplicationLevel));
	}
	
	@RequestMapping(value="/validate")
	public String validate(HttpServletRequest request) {
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			int result = Integer.parseInt(request.getParameter("result"));
		
			request.setAttribute("status", num1 * num2 == result);
			
		} catch (Exception e ) {
			request.setAttribute("status", false);
		}
		
		return this.getMultiplication(request);
		
	}

}
