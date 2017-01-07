package com.sumarlidi.medieval.webbapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sumarlidi.medieval.application.services.MedievalEventService;
import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.domain.User;

@Controller
public abstract class PageController {
	
	@Autowired
	protected MedievalEventService medievalEventService;
	@Autowired
	protected UserService usersService;
	
	@ModelAttribute("listOfEvents")
	public Iterable<MedievalEvent> events(){
		return medievalEventService.getEvents();
	}
	
	@ModelAttribute("user")
	public User getActiveUser(){
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			return usersService.getUserByEmail(email);
		}
		else return null;
		
	}
}
