package com.sumarlidi.medieval.webbapp.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.webbapp.dtos.AddEventsDTO;

@Controller
public class AddEventsController extends PageController {

	
	@GetMapping(value="/add")
	public String addMedievalEventPage(Model model){	
		model.addAttribute("addEventsDTO", new AddEventsDTO() );
		return "add";
	}
	
	@PostMapping(value="/add")
	public String addMedivalEvent( @Valid @ModelAttribute("addEventsDTO")  AddEventsDTO addEventsDTO, BindingResult result, Model model){
		model.addAttribute("addEventsDTO", new AddEventsDTO() );
		if(result.hasErrors()){		
			model.addAttribute(BindingResult.class.getName()+".addEventsDTO", result);			
			return "add";
		}else{			
			createEventAndAdd(addEventsDTO);						
			return "redirect:list";
		}
	}
	
	private void createEventAndAdd(AddEventsDTO addEventsDTO){
		MedievalEvent event = new MedievalEvent();
		event.setName(addEventsDTO.getName());
		event.setPromoter(addEventsDTO.getPromoter());
		event.setMaxParticipants(addEventsDTO.getMaxParticipants());
		event.setDescription(addEventsDTO.getDescription());
		event.setStartDate(addEventsDTO.getStartDate());
		event.setShortDescription(addEventsDTO.getShortDescription());
		event.setAccepted(false);
		medievalEventService.add(event);
	}
}

