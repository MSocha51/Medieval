package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAndAboutController extends PageController {
	
	@RequestMapping({"index","/"})
	public String index(Model model){
		return "redirect:/list";
	}
	
	@RequestMapping("about-me")
	public String aboutMe(Model model){
		return "about-me";
	}

}
