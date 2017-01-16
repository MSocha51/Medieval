package com.sumarlidi.medieval.webbapp.controllers;

import java.util.Optional;

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
	public Iterable<MedievalEvent> events() {
		return medievalEventService.getAcceptedEventsInOrder();
	}
	@ModelAttribute("user")
	public User getUser(){
		return getActiveUser().orElse(null);
	}
	
	public Optional<User> getActiveUser() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			return Optional.ofNullable(usersService.getUserByEmail(email));
		} else
			return Optional.empty();

	}
	@ModelAttribute("listOfUsers")
	public Iterable<User> getListOfUsers(){
		return usersService.getUsers();
	}

	public Boolean checkIfAdminOrMod(User user) {
		try {
			String roleName = user.getRole().getRole();
			if(roleName.equals("ROLE_MOD")||roleName.equals("ROLE_ADMIN")) return true;
			else return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
}
