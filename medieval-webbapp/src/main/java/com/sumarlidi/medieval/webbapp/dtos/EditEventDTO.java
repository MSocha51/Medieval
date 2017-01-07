package com.sumarlidi.medieval.webbapp.dtos;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EditEventDTO {
	@NotBlank(message="Cannot be blank")
	@Size(min=3, message="Must be at least 3 letter size")
	private String name;
	
	private String description;
	
	private String shortDescription;	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	
	
}