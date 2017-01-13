package com.sumarlidi.medieval.webbapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumarlidi.medieval.domain.User;
@Controller
@PreAuthorize(value = "isAuthenticated()")
@RequestMapping("profile-{id}")
public class ProfileController extends PageController {
	@GetMapping
	public String getProfile(@PathVariable("id") Long id,Model model){
		User user =  usersService.getUserById(id);
		if(user==null) return "redirect:list";
		model.addAttribute("userProfile",user);
		return "profile";
	}
	
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("/delete")
	public String deleteUser(@PathVariable("id") Long id){
		usersService.deleteUser(id);
		return "redirect:/users";
	}
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("/promote")
	public String promoteUser(@PathVariable("id") Long id){
		usersService.promoteUser(id);
		return "redirect:/users";
	}
}
