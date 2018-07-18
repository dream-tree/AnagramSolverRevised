package com.marcin.anagramator.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;

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
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramsListDAO.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Anagram> getAnagramsList(String alphabetizedQuery) {	
		Session session = sessionFactory.getCurrentSession();
		List<Anagram> tempList = null;
		try {
			Alphabetized tempAlphabetized = 
					session.createQuery(
								"SELECT alpha " +
								"FROM Alphabetized alpha " +
								"JOIN FETCH alpha.anagrams " + 
								"WHERE alpha.alphabetizedWord=:theWord", Alphabetized.class)	
							.setParameter("theWord", alphabetizedQuery)
							.getSingleResult();
			tempList = tempAlphabetized.getAnagrams();	
		} catch (Exception ex) {
			LOGGER.info("Hibernate database query error.", ex.toString(), ex);
			System.err.println(ex.toString());
		}
		return tempList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveAnagramsList(String userAlphabetizedString, List<String> userListOfStrings) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Alphabetized userAlphabetizedObject = new Alphabetized(1, userAlphabetizedString);
			createListOfAngrams(userAlphabetizedObject, userListOfStrings);
			session.save(userAlphabetizedObject); 	
		} catch (Exception ex) {
			LOGGER.info("Hibernate database save error.", ex.toString(), ex);
		}
	}
	
	public void createListOfAngrams(Alphabetized userAlphabetizedObject, List<String> userListOfStrings) {
		for(String singleString : userListOfStrings) {
			Anagram userSingleAnagram = new Anagram(1, singleString);
			userAlphabetizedObject.add(userSingleAnagram);
		}
	}
}