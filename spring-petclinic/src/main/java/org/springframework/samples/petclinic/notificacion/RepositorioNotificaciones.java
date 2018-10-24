package org.springframework.samples.petclinic.notificacion;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public class RepositorioNotificaciones{}
/*
public interface RepositorioNotificaciones extends Repository<Notificacion, Integer> {
	/*
	 @Transactional(readOnly = true)
	    @Cacheable("nots")
	    Collection<Notificacion> findAll() throws DataAccessException;
	    
}
*/