package com.sumarlidi.medieval.webbapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumarlidi.medieval.domain.MedievalEvent;
@Controller
public class EventsListController extends PageController{
	
	@RequestMapping("/list")
	public String medievalEventsList(Model model){
		Iterable<MedievalEvent> list = events();
		if(!list.iterator().hasNext()) model.addAttribute("message", "There are no incoming events" );
		model.addAttribute("eventList", list);
		return "list";
	}
	
	@RequestMapping("/calendar")
	public String calendar(Model model){
		Iterable<Date> listOfDates = medievalEventService.getEventsDate();
		model.addAttribute("eventsDates", listOfDates);
		return "calendar";
	}
	
	@RequestMapping("events-list-date-{date}")
	public String dateEventList(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, Model model){
		Iterable<MedievalEvent> list = medievalEventService.getEventByDate(date);
		model.addAttribute("eventList", list );
		if(!list.iterator().hasNext()) model.addAttribute("message", "There are no events this day" );		
		else{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("message", "Events from "+ format.format(date));
		}
		return "list";
	}

}
