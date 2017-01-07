package com.sumarlidi.medieval.webbapp.dtos;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class AddEventsDTO {

	@NotBlank(message="Cannot be blank")
	@Size(min=3, message="Must be at least 3 letter size")
	private String name;
	
	@NotBlank(message="Cannot be blank")
	private String promoter;
	
	@NotNull(message="Cannot be blank")
	@Future(message="Date should relate to future")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	private String description;
	
	private String shortDescription;
	@Range(min=0, message="Number of participants must by higher than 0")
	@Digits(message="Must be a number", fraction = 0, integer = 3)
	private int maxParticipants;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	
	
}
