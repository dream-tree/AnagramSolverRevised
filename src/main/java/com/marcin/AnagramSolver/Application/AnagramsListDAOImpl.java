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
			LOGGER.info("Hibernate database query.", ex.toString(), ex);
		} finally {
			session.close();
		}	
		return tempList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAnagramsList(String newEntryAlphabetized, List<String> newEntryAnagramsString) {
		SessionFactory sessionFactory = HibernateUtility2.getInstance();			
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();	
			Alphabetized alphabetized = new Alphabetized(1, newEntryAlphabetized);
			List<Anagram> list = new ArrayList<>();
			for(String singleAnagram : newEntryAnagramsString) {
				Anagram newEntryAnagram = new Anagram(1, singleAnagram);
				list.add(newEntryAnagram);
				alphabetized.add(newEntryAnagram);
		//		session.save(newEntryAnagram);	
			}
			alphabetized.setAnagrams(list);
			session.save(alphabetized);	
			for(Anagram anagram : list) {
				session.save(anagram);	
			}
			session.getTransaction().commit();			
		} catch (Exception ex) {
			LOGGER.info("Hibernate database save.", ex.toString(), ex);
		} finally {
			session.close();
		}		
	}
}