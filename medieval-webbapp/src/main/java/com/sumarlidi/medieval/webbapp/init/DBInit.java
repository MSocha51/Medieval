package com.sumarlidi.medieval.webbapp.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumarlidi.medieval.application.services.RoleService;

@Component
public class DBInit implements InitializingBean {

	@Autowired
	private RoleService roleService; 
	@Override
	public void afterPropertiesSet() throws Exception {
		roleInit();
	}
	private void roleInit() {	
		roleService.createRoleIfNotExist("ROLE_USER");
		roleService.createRoleIfNotExist("ROLE_ADMIN");
		roleService.createRoleIfNotExist("ROLE_MOD");
	}

}
