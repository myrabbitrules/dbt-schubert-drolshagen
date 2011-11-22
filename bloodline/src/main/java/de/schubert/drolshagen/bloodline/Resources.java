package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Provides resources for simple CDI injection.
 * 
 * @author Richard Schubert
 */
public class Resources {

	@SuppressWarnings("unused")
	@PersistenceContext
	@Produces
	@Dependent
	private EntityManager em;
	
	@Produces
	@Dependent
	Logger getLogger(InjectionPoint injectionPoint) {
		// get name of class of attribute into which this logger is injected
		String category = injectionPoint.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}
	
}
