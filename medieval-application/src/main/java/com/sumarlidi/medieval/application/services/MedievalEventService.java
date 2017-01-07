package com.sumarlidi.medieval.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumarlidi.medieval.application.daos.MedievalEventDAO;
import com.sumarlidi.medieval.domain.MedievalEvent;
@Service
public class MedievalEventService {
	
	@Autowired
	private MedievalEventDAO medievalEventDao;

	public MedievalEvent getEventById(Long id){
		return medievalEventDao.findOne(id);
	}

	public Iterable<MedievalEvent> getEvents() {
		return medievalEventDao.findAll();
	}
	public Iterable<MedievalEvent> getAcceptedEvents(){
		return medievalEventDao.findByAccepted(true);
	}

	public void add(MedievalEvent event) {
		medievalEventDao.save(event);
		
	}
}
