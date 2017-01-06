package com.sumarlidi.medieval.webbapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.User;
import com.sumarlidi.medieval.webbapp.dtos.RegisterDTO;

@Controller
public class LoginAndRegisterController extends PageController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping(value="/register")
	public String registerPage(Model model){
		model.addAttribute("registerDTO" , new RegisterDTO());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String registerUser( @Valid @ModelAttribute("registerDTO")  RegisterDTO registerDTO, BindingResult result, Model model){	
		model.addAttribute("registerDTO" , new RegisterDTO());
		if(result.hasErrors()){
			model.addAttribute(BindingResult.class.getName()+".registerDTO", result);
			return "register";
		}else{
			User newUser = new User();
			newUser.setNick(registerDTO.getNick());
			newUser.setEmail(registerDTO.getEmail());
			newUser.setTeam(registerDTO.getTeam());
			newUser.setPassword(registerDTO.getPassword());
			userService.addUser(newUser);
			return "redirect:list";
		}
	}
	
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
	
}