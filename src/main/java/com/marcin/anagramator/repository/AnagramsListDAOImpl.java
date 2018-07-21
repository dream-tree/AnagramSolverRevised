package com.marcin.anagramator.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramsListDAO.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Anagram> getAnagramsList(String alphabetizedQuery) {	
		List<Anagram> tempList = null;
		try {
			Alphabetized tempAlphabetized = 
					entityManager.createQuery(
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
		try {
			Alphabetized userAlphabetizedObject = new Alphabetized(1, userAlphabetizedString);
			createListOfAngrams(userAlphabetizedObject, userListOfStrings);
			entityManager.persist(userAlphabetizedObject); 	
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