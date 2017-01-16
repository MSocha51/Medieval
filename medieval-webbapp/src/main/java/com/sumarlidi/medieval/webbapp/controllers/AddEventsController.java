package com.sumarlidi.medieval.webbapp.controllers;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.HtmlUtils;

import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.webbapp.dtos.AddEventsDTO;

@Controller
@PreAuthorize(value = "isAuthenticated()")
public class AddEventsController extends PageController {

	@GetMapping(value = "/add")
	public String addMedievalEventPage(Model model) {
		model.addAttribute("addEventsDTO", new AddEventsDTO());
		return "add";
	}

	@PostMapping(value = "/add")
	public String addMedivalEvent(@Valid @ModelAttribute("addEventsDTO") AddEventsDTO addEventsDTO,
			BindingResult result, Model model) {
	
		if (result.hasErrors()) {
			model.addAttribute("addEventsDTO", addEventsDTO);
			//model.addAttribute(BindingResult.class.getName() + ".addEventsDTO", result);
			return "add";
		} else {
			createEventAndAdd(addEventsDTO);
			return "redirect:list";
		}
	}

	private void createEventAndAdd(AddEventsDTO addEventsDTO) {
		MedievalEvent event = new MedievalEvent();
		EncodeHtmlEntities(addEventsDTO);
		event.setName(addEventsDTO.getName());
		event.setPromoter(addEventsDTO.getPromoter());
		event.setMaxParticipants(Integer.parseInt(addEventsDTO.getMaxParticipants()));
		event.setDescription(addEventsDTO.getDescription());
		event.setStartDate(addEventsDTO.getStartDate());
		event.setShortDescription(addEventsDTO.getShortDescription());
		event.setAccepted(false);
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		event.setOwner(usersService.getUserByEmail(email));
		medievalEventService.add(event);
	}

	private void EncodeHtmlEntities(AddEventsDTO addEventsDTO) {
		addEventsDTO.setName(HtmlUtils.htmlEscape(addEventsDTO.getName(), "UTF-8"));
		addEventsDTO.setPromoter(HtmlUtils.htmlEscape(addEventsDTO.getPromoter(), "UTF-8"));
		addEventsDTO.setDescription(HtmlUtils.htmlEscape(addEventsDTO.getDescription(), "UTF-8"));
		addEventsDTO.setShortDescription(HtmlUtils.htmlEscape(addEventsDTO.getShortDescription(), "UTF-8"));
	}
}
