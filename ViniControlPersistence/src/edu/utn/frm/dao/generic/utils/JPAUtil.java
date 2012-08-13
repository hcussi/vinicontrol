package edu.utn.frm.dao.generic.utils;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {
	
	private static Logger logger = Logger.getLogger(JPAUtil.class.getName());
	private static final EntityManagerFactory entityManagerFactory;
	
	static {
		try {
			 entityManagerFactory = Persistence.createEntityManagerFactory("ViniControlPersistencePU");
		} catch (Throwable ex) {
			// Log exception!
			//logger.error(ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager(){
		
		return entityManagerFactory.createEntityManager();
	}
}