package com.sumarlidi.medieval.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumarlidi.medieval.application.daos.MedievalEventDAO;
import com.sumarlidi.medieval.domain.MedievalEvent;
@Service
public class MedievalEventService {
	
	@Autowired
	private MedievalEventDAO medievalEventDao;
	
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
	public Iterable<MedievalEvent> getAcceptedEvents(){
		return medievalEventDao.findByAccepted(true);
	}
	public Iterable<MedievalEvent> getUnacceptedEvents(){
		return medievalEventDao.findByAccepted(false);
	}

	public void add(MedievalEvent event) {
		medievalEventDao.save(event);
		
	}

	public void deleteEvent(Long id) {
		medievalEventDao.delete(id);		
	}


}
