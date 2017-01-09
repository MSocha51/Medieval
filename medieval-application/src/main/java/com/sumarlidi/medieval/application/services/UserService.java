package com.sumarlidi.medieval.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumarlidi.medieval.application.daos.MedievalEventDAO;
import com.sumarlidi.medieval.application.daos.RoleDAO;
import com.sumarlidi.medieval.application.daos.UserDAO;
import com.sumarlidi.medieval.domain.MedievalEvent;
import com.sumarlidi.medieval.domain.Role;
import com.sumarlidi.medieval.domain.User;
import com.sumarlidi.medieval.webbapp.exceptions.EventLackOfVacanciesException;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private MedievalEventDAO eventDao;

	@Transactional
	public void addUser(User user) {
		Role role = roleDao.findByRole("ROLE_USER");
		user.setRole(role);
		userDao.save(user);
	}
	public void addUserWithRole(User user, String roleName){
		Role role = roleDao.findByRole(roleName);
		user.setRole(role);
		userDao.save(user);		
	}

	public User getUserById(Long id) {
		return userDao.findOne(id);
	}

	public Iterable<User> getUsers() {
		return userDao.findAll();
	}

	public User getUserByEmail(String Email) {
		return userDao.findByEmail(Email);

	}

	@Transactional
	public void signUserForEvent(String userEmail, Long eventId) throws EventLackOfVacanciesException{
		MedievalEvent event = eventDao.findOne(eventId);
		Long maxParticipants = event.getMaxParticipants().longValue();
		Long participants = (long) event.getParticipants().size();
		if(maxParticipants <= participants) 
			throw new EventLackOfVacanciesException("Event: "+event.getName()+" full", maxParticipants);
		User user = getUserByEmail(userEmail);
		user.getSignedEvents().add(event);
		userDao.save(user);
	}

	public boolean ifUserNickExist(String userNick) {
		return userDao.existsByNick(userNick);
	}

	public boolean ifUserEmailExist(String userEmail) {
		return userDao.existsByEmail(userEmail);
	}
	public void addIfNotExist(User user, String roleName) {
		if(!ifUserEmailExist(user.getEmail())){
			addUserWithRole(user,roleName);
		}
		
	}
}
