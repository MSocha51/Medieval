package com.sumarlidi.medieval.application.daos;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sumarlidi.medieval.domain.MedievalEvent;



@Repository
public interface MedievalEventDAO extends CrudRepository<MedievalEvent,Long>{

	public Iterable<MedievalEvent> findByAccepted(@Param("accepted") Boolean accepted);
	
	public Iterable<MedievalEvent> findByAcceptedAndStartDateAfterOrderByStartDateAsc(@Param("accepted") Boolean accepted,@Param("date") Date date);
	
	@Query("SELECT DISTINCT e.startDate FROM MedievalEvent e WHERE e.accepted = :accepted ORDER BY e.startDate DESC")
	public Iterable<Date> findDistinctStartDateByAccepted(@Param("accepted") Boolean accepted);

	public Iterable<MedievalEvent> getEventByStartDateAndAccepted(Date startDate, Boolean accepted);
}
