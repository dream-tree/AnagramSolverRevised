package com.marcin.AnagramSolver.Application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class constructs the Hibernate ORM' SessionFactory singleton object 
 * in the lazy initialization mode.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public class HibernateUtility2 {
	
	private HibernateUtility2() {
	}
	
	/**
	 * Inner static class constructing the SessionFactory object.
	 * @author oVoISheRe
	 */
	private static class SessionFactoryHelper {
		private static final SessionFactory SESSION_FACTORY = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Alphabetized.class)
				.addAnnotatedClass(Anagram.class)
				.buildSessionFactory();
	}
	
	/**
	 * Public method returning the SessionFactory singleton object.
	 * @return SessionFactory singleton object
	 */
	public static SessionFactory getInstance() {
		return SessionFactoryHelper.SESSION_FACTORY;
	}	
}