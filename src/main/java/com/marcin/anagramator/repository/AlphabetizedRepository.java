package com.marcin.anagramator.repository;

import com.marcin.anagramator.domain.Alphabetized;

/**
 * Provides the interface for CRUD operations and common queries, i.e.
 * for accessing, adding and deleting an Alphabeized objects.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
public interface AlphabetizedRepository {
		
	/**
	 * Gets all matching words from the database that are the anagrams of the user query (string of characters).
	 * The result is returned as an Alphabetized object. 
	 * 
	 * @param alphabetizedWord
	 *            string of characters from the user query sorted lexicographically
	 * @return Alphabetized object containing an alphabetized word and all matching
	 *         anagrams to be displayed on the result web page
	 */
	public Alphabetized getAlphabetized(String alphabetizedWord);

	/**
	 * Saves a new word and all of its anagrams (optionally) in the database. 
	 * New word means a word which does not exist in the database yet.
	 * Anagrams are stored in the database as an Alphabetized object.
	 * 
	 * @param userAlphabetizedObject
	 *            an Alphabetized object to be added to the database
	 * @return Alphabetized object containing an alphabetized word and all matching
	 *         anagrams saved in the database
	 */
	public Alphabetized saveAlphabetized(Alphabetized userAlphabetizedObject);
	
	/**
	 * Removes an Alphabetized object from the database. 
	 * 
	 * @param alphabetizedWord
	 *            string of characters from the user query sorted lexicographically
	 */
	public void deleteAlphabetized(String alphabetizedWord);

}
