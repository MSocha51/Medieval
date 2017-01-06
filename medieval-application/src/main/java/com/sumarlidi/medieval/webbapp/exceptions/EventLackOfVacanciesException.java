package com.sumarlidi.medieval.webbapp.exceptions;

public class EventLackOfVacanciesException extends Exception {
	
	private Long vacancies;

	public EventLackOfVacanciesException(String message, Long numberOfVacancies) {
		super(message);
		vacancies = numberOfVacancies;
	}

	public Long getVacancies() {
		return vacancies;
	}

	

}
