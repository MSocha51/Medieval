package com.sumarlidi.medieval.webbapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.User;
import com.sumarlidi.medieval.webbapp.dtos.EditEventDTO;
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
			createUserAndAdd(registerDTO);			
			return "redirect:list";
		}
	}
	
    private void createUserAndAdd(RegisterDTO registerDTO) {
		User newUser = new User();
		EncodeHtmlEntities(registerDTO);
		newUser.setNick(registerDTO.getNick());
		newUser.setEmail(registerDTO.getEmail());
		newUser.setTeam(registerDTO.getTeam());
		newUser.setPassword(registerDTO.getPassword());
		userService.addUser(newUser);
	}
    
    private void EncodeHtmlEntities(RegisterDTO registerDTO) {
    	registerDTO.setNick(HtmlUtils.htmlEscape(registerDTO.getNick(), "UTF-8"));
    	registerDTO.setTeam(HtmlUtils.htmlEscape(registerDTO.getTeam(), "UTF-8"));
    }

	@GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	
}
