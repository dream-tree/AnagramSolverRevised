package com.marcin.anagramator.repository;

import java.util.List;

import com.marcin.anagramator.domain.Anagram;

/**
 * Base interface for CRUD operations and common queries.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public interface AnagramsListDAO {

	/**
	 * Gets all matching words from the database that are anagrams of the user query string.
	 * @param alphabetizedWord string of letters from user query sorted lexicographically
	 * @return string of anagrams
	 */
	public List<Anagram> getAnagramsList(String alphabetized);
	
	/**
	 * Saves new word and all of its anagrams (optionally) to the database. 
	 * New word means a word which does not exist in the database yet. 
	 * @param newEntryAlphabetized alphabetized word to be added to the database
	 * @param newEntryAnagramsString list of strings (anagrams) to be added to the database
	 */
	public void saveAnagramsList(String newEntryAlphabetized, List<String> newEntryAnagramsString);
	
}
