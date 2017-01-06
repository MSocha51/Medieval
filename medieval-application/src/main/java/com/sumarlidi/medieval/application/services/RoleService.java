package com.sumarlidi.medieval.application.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumarlidi.medieval.application.daos.RoleDAO;
import com.sumarlidi.medieval.domain.Role;
@Service
public class RoleService {

	@Autowired
	private RoleDAO roleDao;
	@Transactional
	public void createRoleIfNotExist(String roleName){
		Role role = roleDao.findByRole(roleName);
		if(role==null){
			role = new Role();
			role.setRole(roleName);
			roleDao.save(role);
		}		
	}
}
