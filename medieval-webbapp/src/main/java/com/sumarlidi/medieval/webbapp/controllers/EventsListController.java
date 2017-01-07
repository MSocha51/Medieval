package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class EventsListController extends PageController{
	
	@RequestMapping(value={"/list","/"})
	public String medievalEventsList(Model model){
		return "list";
	}

}
