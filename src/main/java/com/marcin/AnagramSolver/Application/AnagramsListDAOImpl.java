package com.marcin.AnagramSolver.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



/**
 * DAO class implementing the {@link AnagramsListDAO} interface
 * for CRUD operations and the common queries.
 * This implementation uses the Hibernate ORM framework.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Repository
public class AnagramsListDAOImpl implements AnagramsListDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramsListDAO.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Anagram> getAnagramsList(String alphabetizedQuery) {		
		SessionFactory sessionFactory = HibernateUtility2.getInstance();			
		Session session = sessionFactory.getCurrentSession();
		List<Anagram> tempList = null;
		try {
			session.beginTransaction();	
			Query<Alphabetized> query = session.createQuery("select alpha from Alphabetized alpha "
									+ "JOIN FETCH alpha.anagrams "
									+ "where alpha.alphabetizedWord=:theWord", Alphabetized.class);	
			query.setParameter("theWord", alphabetizedQuery);
			Alphabetized tempAlphabetized = query.getSingleResult();
			tempList = tempAlphabetized.getAnagrams();
			session.getTransaction().commit();			
		} catch (Exception ex) {
			LOGGER.info("Hibernate database query error.", ex.toString(), ex);
		} finally {
			session.close();
		}	
		return tempList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAnagramsList(String userAlphabetizedString, List<String> userListOfStrings) {
		SessionFactory sessionFactory = HibernateUtility2.getInstance();			
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();	
			Alphabetized userAlphabetizedObject = new Alphabetized(1, userAlphabetizedString);
			List<Anagram> listOfAngrams = createListOfAngrams(userAlphabetizedObject, userListOfStrings);
			session.save(userAlphabetizedObject); 
			session.getTransaction().commit();		
		} catch (Exception ex) {
			LOGGER.info("Hibernate database save error.", ex.toString(), ex);
		} finally {
			session.close();	
		}
	}
	
	public List<Anagram> createListOfAngrams(Alphabetized userAlphabetizedObject, List<String> userListOfStrings) {
		List<Anagram> listOfAngrams = new ArrayList<>();
		for(String singleString : userListOfStrings) {
			Anagram userSingleAnagram = new Anagram(1, singleString);
			listOfAngrams.add(userSingleAnagram);
			userAlphabetizedObject.add(userSingleAnagram);
		}
		return listOfAngrams;
	}
}