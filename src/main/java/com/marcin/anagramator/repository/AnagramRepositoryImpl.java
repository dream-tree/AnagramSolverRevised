package com.marcin.anagramator.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcin.anagramator.domain.Alphabetized;

/**
 * DAO class implementing the {@link AnagramRepository} interface
 * for CRUD operations and the common queries.
 * This implementation uses the Hibernate ORM framework.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Repository
public class AnagramRepositoryImpl implements AnagramRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagramRepository.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Alphabetized getAnagrams(String alphabetizedQuery) {	
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
	public Alphabetized saveAnagrams(Alphabetized userAlphabetizedObject) {
		try {
			entityManager.persist(userAlphabetizedObject); 				
		} catch (Exception ex) {
			LOGGER.info("Hibernate database save error.", ex.toString(), ex);
		}
		return userAlphabetizedObject;
	}
}