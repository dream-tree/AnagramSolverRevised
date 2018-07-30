package com.marcin.anagramator.repository;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;

/**
 * DAO class implementing the {@link AnagramListDAO} interface
 * for CRUD operations and the common queries.
 * This implementation uses the Hibernate ORM framework.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Repository
public class AnagramListDAOImpl implements AnagramListDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramListDAO.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Alphabetized getAlphabetized(String alphabetizedQuery) {	
		Alphabetized tempAlphabetized = new Alphabetized();
		try {
			tempAlphabetized = 
					entityManager.createQuery(
								"SELECT alpha " +
								"FROM Alphabetized alpha " +
								"JOIN FETCH alpha.anagrams " + 
								"WHERE alpha.alphabetizedWord=:theWord", Alphabetized.class)	
					.setParameter("theWord", alphabetizedQuery)
					.getSingleResult();
		} catch (Exception ex) {
			LOGGER.info("Hibernate database query error.", ex.toString(), ex);
		}
		return tempAlphabetized;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveAnagramsList(String userAlphabetizedString, Set<String> userListOfStrings) {
		try {
			Alphabetized userAlphabetizedObject = new Alphabetized(1, userAlphabetizedString);
			createListOfAngrams(userAlphabetizedObject, userListOfStrings);
			entityManager.persist(userAlphabetizedObject); 	
		} catch (Exception ex) {
			LOGGER.info("Hibernate database save error.", ex.toString(), ex);
		}
	}
	
	public void createListOfAngrams(Alphabetized userAlphabetizedObject, Set<String> userListOfStrings) {
		for(String singleString : userListOfStrings) {
			Anagram userSingleAnagram = new Anagram(1, singleString);
			userAlphabetizedObject.add(userSingleAnagram);
		}
	}
}