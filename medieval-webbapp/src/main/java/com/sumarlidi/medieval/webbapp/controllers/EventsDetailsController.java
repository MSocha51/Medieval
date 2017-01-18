package com.sumarlidi.medieval.webbapp.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.domain.User;
import com.sumarlidi.medieval.webbapp.dtos.AddEventsDTO;
import com.sumarlidi.medieval.webbapp.dtos.EditEventDTO;
import com.sumarlidi.medieval.webbapp.exceptions.EventLackOfVacanciesException;

@Controller
public class EventsDetailsController extends PageController {

	@Autowired
	private UserService userService;

	@RequestMapping({ "/event-details-{id}", "/event-{id}" })
	public String eventDetails(@PathVariable("id") Long id, Model model, String vacanices) {
		if (vacanices!=null) {
			model.addAttribute("eventFullMsg", "Event is full");
		}
		MedievalEvent event = medievalEventService.getEventById(id);
		if(event==null) return "redirect:/list";
		Boolean ifOwnerOrMod;
		if (checkIfActiveUserIsModOrOwner(event))
			ifOwnerOrMod = true;
		else
			ifOwnerOrMod = false;
		model.addAttribute("ifOwnerOrMod", ifOwnerOrMod);
		model.addAttribute("medievalEvent", event);
		return "event-details";

	}

	@PreAuthorize(value = "isAuthenticated()")
	@PostMapping({ "/event-details-{id}/sing", "/event-{id}/sing" })
	public String singUserOnEvent(@PathVariable("id") Long eventId, Model model) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		String email = principal.getName();
		try {
			userService.signUserForEvent(email, eventId);
		} catch (EventLackOfVacanciesException e) {
			return "redirect:/event-details-" + eventId + "?vacanices=false";
		}
		return "redirect:/event-details-" + eventId;
	}

	@PreAuthorize(value = "isAuthenticated()")
	@GetMapping({ "/event-details-{id}/edit", "/event-{id}/edit" })
	public String getEditEvent(@PathVariable("id") Long eventId, Model model) {
		MedievalEvent event = medievalEventService.getEventById(eventId);
		if (checkIfActiveUserIsModOrOwner(event)) {
			EditEventDTO editDto = ecrateEditEventDTOAndAddAttribute(event);
			model.addAttribute("medievalEvent", event);
			model.addAttribute("editEventDto", editDto);
			return "edit-event";
		} else {
			return "redirect:/event-details-" + eventId;
		}
	}

	@PreAuthorize(value = "isAuthenticated()")
	@PostMapping({ "/event-details-{id}/edit", "/event-{id}/edit" })
	public String postEditEvent(@Valid @ModelAttribute("editEventDto") EditEventDTO editDto, BindingResult result,
			@PathVariable("id") Long eventId, Model model) {
		MedievalEvent event = medievalEventService.getEventById(eventId);
		if (checkIfActiveUserIsModOrOwner(event)) {
			if (result.hasErrors()) {
				model.addAttribute(BindingResult.class.getName() + ".editEventDto", result);
				return "edit-event";
			} else {
				changeEventAndAdd(editDto, event);
				return "redirect:/event-details-" + eventId;
			}
		} else {
			return "redirect:/event-details-" + eventId;
		}
	}

	@PreAuthorize(value = "hasAnyRole('ROLE_MOD','ROLE_ADMIN')")
	@RequestMapping({ "/event-details-{id}/delete", "/event-{id}/delete" })
	public String deleteEvent(@PathVariable("id") Long id) {
		medievalEventService.deleteEvent(id);
		return "redirect:/list";
	}

	protected void changeEventAndAdd(EditEventDTO editDto, MedievalEvent event) {
		EncodeHtmlEntities(editDto);
		event.setDescription(editDto.getDescription());
		event.setShortDescription(editDto.getShortDescription());
		event.setName(editDto.getName());
		medievalEventService.add(event);
	}

	protected void EncodeHtmlEntities(EditEventDTO editDto) {
		editDto.setName(HtmlUtils.htmlEscape(editDto.getName(), "UTF-8"));
		editDto.setDescription(HtmlUtils.htmlEscape(editDto.getDescription(), "UTF-8"));
		editDto.setShortDescription(HtmlUtils.htmlEscape(editDto.getShortDescription(), "UTF-8"));
	}

	protected EditEventDTO ecrateEditEventDTOAndAddAttribute(MedievalEvent event) {
		EditEventDTO editDto = new EditEventDTO();
		editDto.setName(event.getName());
		editDto.setDescription(event.getDescription());
		editDto.setShortDescription(event.getShortDescription());
		return editDto;
	}

	protected Boolean checkIfActiveUserIsModOrOwner(MedievalEvent event) {
		Optional<User> user = getActiveUser();
		if(user.isPresent()){
			return chechIfModOrOwner(user.get(),event);
		}
		else return true;
	}

	private Boolean chechIfModOrOwner(User user, MedievalEvent event) {
		if(user.equals(event.getOwner()))
			return true;
		else return checkIfAdminOrMod(user);
	}
}