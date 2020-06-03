package com.albertsalud.mathsbasics.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/restas")
public class RemainderController {
	
	private int remainderLevel;
	
	@RequestMapping(value="/setLevel")
	public String setLevel(HttpServletRequest request) {
		remainderLevel = Integer.parseInt(request.getParameter("remainderLevel"));
		return "redirect:/restas/getRemainder";
	}
	
	@RequestMapping(value="/getRemainder")
	public String getRemainder(HttpServletRequest request) {
		request.setAttribute("remainderLevel", remainderLevel);
		
		int num1 = (int) generateRandomNumber();
		int num2 = (int) generateRandomNumber();
		
		request.setAttribute("num1", num1 >= num2 ? num1 : num2);
		request.setAttribute("num2", num1 >= num2 ? num2 : num1);
		
		return "restas";
	}

	private Object generateRandomNumber() {
		return new Random().nextInt((int) Math.pow(10, remainderLevel));
	}
	
	@RequestMapping(value="/validate")
	public String validate(HttpServletRequest request) {
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			int result = Integer.parseInt(request.getParameter("result"));
		
			request.setAttribute("status", num1 - num2 == result);
			
		} catch (Exception e ) {
			request.setAttribute("status", false);
		}
		
		return this.getRemainder(request);
		
	}

}
