package com.sumarlidi.medieval.application.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sumarlidi.medieval.domain.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.nick = :userNick")
	public boolean existsByNick(@Param("userNick") String userNick);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :userEmail")
	public boolean existsByEmail(@Param("userEmail") String userEmail);
	
	public User findByEmailIgnoreCase(@Param("Email")String email);

}
