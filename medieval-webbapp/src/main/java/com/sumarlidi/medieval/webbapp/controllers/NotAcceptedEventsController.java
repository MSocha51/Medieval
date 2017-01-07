package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotAcceptedEventsController extends PageController {
	
	
	
	@RequestMapping("/notAcceptedEvent")
	public String getNotAcceptedEvent(Model model){
		model.addAttribute("notAcceptedListOfEvents", medievalEventService.getUnacceptedEvents());
		return "not-accepted-event";
	}
}
