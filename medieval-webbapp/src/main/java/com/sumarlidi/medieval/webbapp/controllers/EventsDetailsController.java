package com.sumarlidi.medieval.webbapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.domain.User;
import com.sumarlidi.medieval.webbapp.exceptions.EventLackOfVacanciesException;

@Controller
public class EventsDetailsController extends PageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/event-details-{id}")
	public String eventDetails(@PathVariable("id") Long id ,Model model,String vacanices){
		if("fasle".equals(vacanices)){
			model.addAttribute("eventFullMsg","Event is full");			
		}
		MedievalEvent event = medievalEventService.getEventById(id);
		model.addAttribute("medievalEvent", event);
		return "event-details";
		
		
	}
	@PreAuthorize(value = "isAuthenticated()")
	@PostMapping("/event-details-{id}/sing")
	public String singUserOnEvent(@PathVariable("id") Long eventId ,Model model){
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		String email = principal.getName();
		try{
		userService.signUserForEvent(email, eventId);
		}catch(EventLackOfVacanciesException e){
			return "redirect:/event-details-"+eventId+"?vacanices=false";
		}
		return "redirect:/event-details-"+eventId;		
	}


}
;