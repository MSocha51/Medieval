package com.sumarlidi.medieval.application.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumarlidi.medieval.application.daos.MedievalEventDAO;
import com.sumarlidi.medieval.application.daos.UserDAO;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.domain.User;
@Service
public class MedievalEventService {
	
	@Autowired
	private MedievalEventDAO medievalEventDao;
	@Autowired
	private UserDAO userDao;
	
	public void acceptEvent(Long id) {
		MedievalEvent event = getEventById(id);
		event.setAccepted(true);
		add(event);
	}

	public MedievalEvent getEventById(Long id){
		return medievalEventDao.findOne(id);
	}

	public Iterable<MedievalEvent> getEvents() {
		return medievalEventDao.findAll();
	}
	public Iterable<MedievalEvent> getAcceptedEventsInOrder(){
		return medievalEventDao.findByAcceptedAndStartDateAfterOrderByStartDateAsc(true, new Date());
	}
	public Iterable<MedievalEvent> getUnacceptedEvents(){
		return medievalEventDao.findByAccepted(false);
	}
	public Iterable<Date> getEventsDate(){
		return medievalEventDao.findDistinctStartDateByAccepted(true);
	}

	public void add(MedievalEvent event) {
		medievalEventDao.save(event);
		
	}

	public void deleteEvent(Long id) {		
		medievalEventDao.delete(id);		
	}

	public Iterable<MedievalEvent> getEventByDate(Date date) {
		return medievalEventDao.getEventByStartDateAndAccepted(date, true);
	}


}
