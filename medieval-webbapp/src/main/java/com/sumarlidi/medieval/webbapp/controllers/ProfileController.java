package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@PreAuthorize(value = "isAuthenticated()")
public class ProfileController extends PageController {
	@GetMapping("profile")
	public String getProfile(Model model){
		return "profile";
	}

}
