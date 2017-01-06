package com.sumarlidi.medieval.webbapp.dtos;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.sumarlidi.medieval.application.validators.EmailNotExist;
import com.sumarlidi.medieval.application.validators.UserNotExist;

public class RegisterDTO {
	@UserNotExist
	@NotBlank(message = "Cannot be blank")
	@Size(min = 3, message = "Must be at least 3 letter size")
	private String nick;
	@Email
	@NotBlank
	@EmailNotExist
	private String email;
	private String team;
	@NotBlank
	@Size(min = 8, message = "Must be at least 8 letter size")
	private String password;
	@NotBlank
	@Size(min = 8, message = "Must be at least 8 letter size")
	private String passwordRepetition;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepetition() {
		return passwordRepetition;
	}

	public void setPasswordRepetition(String passwordRepetition) {
		this.passwordRepetition = passwordRepetition;
	}

}
