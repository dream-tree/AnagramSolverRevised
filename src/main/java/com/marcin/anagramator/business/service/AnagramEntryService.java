package com.marcin.anagramator.business.service;

import java.util.Set;

/**
 * Provides the service for creating and validating anagrams from user 
 * input to allow persisting them in the database. 
 * All queries should interact with the database through this interface
 * rather than directly through the repository interface.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public interface AnagramEntryService {

	/**
	 * 
	 * @param userAnagramsString
	 *			user's words (anagrams) typed in the web page form
	 * @return set of unique words; every single returned word is an anagram to each
	 *			other one in the returned set
	 */
	public Set<String> getAnagramsFromUserInput(String userAnagramsString);
	
}
