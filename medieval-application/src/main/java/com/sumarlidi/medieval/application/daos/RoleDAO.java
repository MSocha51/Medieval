package com.sumarlidi.medieval.application.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumarlidi.medieval.domain.Role;

@Repository
public interface RoleDAO extends CrudRepository<Role,Long>{

	public Role findByRole(String role);
}
