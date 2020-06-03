package com.albertsalud.mathsbasics.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/sumas")
public class SumController {
	
	private int sumLevel;
	
	@RequestMapping(value="/setLevel")
	public String setLevel(HttpServletRequest request) {
		sumLevel = Integer.parseInt(request.getParameter("sumLevel"));
		return "redirect:/sumas/getSum";
	}
	
	@RequestMapping(value="/getSum")
	public String getSum(HttpServletRequest request) {
		request.setAttribute("sumLevel", sumLevel);
		
		request.setAttribute("num1", generateRandomNumber());
		request.setAttribute("num2", generateRandomNumber());
		
		return "sumas";
	}

	private Object generateRandomNumber() {
		return new Random().nextInt((int) Math.pow(10, sumLevel));
	}
	
	@RequestMapping(value="/validate")
	public String validate(HttpServletRequest request) {
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			int result = Integer.parseInt(request.getParameter("result"));
		
			request.setAttribute("status", num1 + num2 == result);
			
		} catch (Exception e ) {
			request.setAttribute("status", false);
		}
		
		return this.getSum(request);
		
	}

}
