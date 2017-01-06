package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sumarlidi.medieval.application.services.MedievalEventService;
import com.sumarlidi.medieval.domain.MedievalEvent;

@Controller
public abstract class PageController {
	
	@Autowired
	protected MedievalEventService medievalEventService;
	
	@ModelAttribute("listOfEvents")
	public Iterable<MedievalEvent> events(){
		return medievalEventService.getEvents();
	}
}
