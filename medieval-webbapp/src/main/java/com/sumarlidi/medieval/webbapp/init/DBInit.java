package com.sumarlidi.medieval.webbapp.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumarlidi.medieval.application.services.RoleService;
import com.sumarlidi.medieval.application.services.UserService;
import com.sumarlidi.medieval.domain.User;

@Component
public class DBInit implements InitializingBean {

	@Autowired
	private RoleService roleService; 
	@Autowired 
	private UserService userService;
	@Override
	public void afterPropertiesSet() throws Exception {
		roleInit();
		userInit();
	}
	private void roleInit() {	
		roleService.createRoleIfNotExist("ROLE_USER");
		roleService.createRoleIfNotExist("ROLE_ADMIN");
		roleService.createRoleIfNotExist("ROLE_MOD");
	}
	private void userInit(){
		User admin = new User();
		admin.setEmail("Admin@mail.pl");
		admin.setPassword("Mail");
		admin.setNick("Admin");
		admin.setTeam("AdminTeam");
		userService.addIfNotExist(admin,"ROLE_ADMIN");
	}

}
