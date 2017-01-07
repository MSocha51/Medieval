package com.sumarlidi.medieval.application.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sumarlidi.medieval.domain.MedievalEvent;



@Repository
public interface MedievalEventDAO extends CrudRepository<MedievalEvent,Long>{

	public Iterable<MedievalEvent> findByAccepted(@Param("accepted") Boolean accepted);
}
