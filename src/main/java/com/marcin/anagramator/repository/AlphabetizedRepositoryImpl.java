package com.marcin.anagramator.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcin.anagramator.domain.Alphabetized;

/**
 * Repository implementing the {@link AlphabetizedRepository} interface
 * for CRUD operations and the common queries.
 * This implementation uses the Hibernate ORM framework.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Repository
public class AlphabetizedRepositoryImpl implements AlphabetizedRepository {

	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(AlphabetizedRepositoryImpl.class);

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
			LOGGER.info("Hibernate database fetching error.", ex.toString(), ex);
		}
		return tempAlphabetized;
	}
	
	@Override
	@Transactional
	public Alphabetized saveAlphabetized(Alphabetized userAlphabetizedObject) {
		try {
			entityManager.persist(userAlphabetizedObject); 				
		} catch (Exception ex) {
			LOGGER.info("Hibernate database saving error.", ex.toString(), ex);
		}
		return userAlphabetizedObject;
	}

	@Override
	@Transactional
	public void deleteAlphabetized(String alphabetizedWord) {
		try {
			Alphabetized alpha = entityManager.createQuery(
					"SELECT a FROM Alphabetized a " + 
					"WHERE a.alphabetizedWord=:alpha", Alphabetized.class)
			.setParameter("alpha", alphabetizedWord)
			.getSingleResult();	
			entityManager.remove(alpha);			
		} catch (Exception ex) {
			LOGGER.info("Hibernate database removal error.", ex.toString(), ex);
		}
	}
}
