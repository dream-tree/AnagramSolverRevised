package com.marcin.anagramator.repository;

import com.marcin.anagramator.domain.Alphabetized;

/**
 * Base interface for CRUD operations and common queries.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */

public interface AnagramRepository {

	/**
	 * Gets all matching words from the database that are the anagrams of the user
	 * query (string of characters).
	 * 
	 * @param alphabetized
	 *            string of characters from the user query sorted lexicographically
	 * @return Alphabetized object containing an Alphabetized word and all matching
	 *         anagrams to be displayed on the result web page
	 */
	public Alphabetized getAnagrams(String alphabetized);

	/**
	 * Saves a new word and all of its anagrams (optionally) to the database. 
	 * New word means a word which does not exist in the database yet.
	 * Anagrams are stored in the database as an Alphabetized object.
	 * 
	 * @param userAlphabetizedObject
	 *            an Alphabetized object to be added to the database
	 * @return Alphabetized object containing an alphabetized word and all matching
	 *         anagrams saved in the database
	 */
	public Alphabetized saveAnagrams(Alphabetized userAlphabetizedObject);

}
