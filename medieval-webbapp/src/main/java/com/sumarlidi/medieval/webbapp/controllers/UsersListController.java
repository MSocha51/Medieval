package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersListController extends PageController {
	
	@RequestMapping("/users")
	public String getUsers(Model model){
		return "users-list";
	}
}
