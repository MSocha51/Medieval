package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize(value = "hasAnyRole('ROLE_MOD','ROLE_ADMIN')")
@Controller
public class NotAcceptedEventsController extends PageController {
	
	@RequestMapping("/not-accepted-events")
	public String getNotAcceptedEvent(Model model){
		model.addAttribute("notAcceptedListOfEvents", medievalEventService.getUnacceptedEvents());
		return "not-accepted-event";
	}
	
	@PostMapping("/accept-{id}")
	public String postAcceptEvent(@PathVariable("id") Long id, Model model){
		medievalEventService.acceptEvent(id);
		return "redirect:not-accepted-events";
	}
}
