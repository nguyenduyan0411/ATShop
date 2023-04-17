package com.atteam.atshop.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("messageLogin", "Please login");
		return "web/security/sign-in";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("messageLogin", "Login successfully");
		return "web/security/sign-in";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("messageLogin", "Invalid username or password");
		return "web/security/sign-in";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("messageLogin", "Access denied");
		return "web/security/sign-in";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("messageLogin", "You have signed out");
		return "web/security/sign-in";
	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
}
