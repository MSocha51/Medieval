package com.sumarlidi.medieval.webbapp.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class EventsListController extends PageController{
	
	@RequestMapping("/list")
	public String medievalEventsList(Model model){
		return "list";
	}
	
	@RequestMapping("/calendar")
	public String calendar(Model model){
		Iterable<Date> listOfDates = medievalEventService.getEventsDate();
		model.addAttribute("eventsDates", listOfDates);
		return "calendar";
	}

}
