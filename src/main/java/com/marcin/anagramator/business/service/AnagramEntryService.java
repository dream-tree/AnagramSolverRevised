package com.marcin.anagramator.business.service;

import java.util.Set;

/**
 * Provides the service for parsing and validating anagrams from user 
 * input to allow persisting them in the database. 
 * All queries should interact with the database through this interface
 * rather than directly through the repository interface.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
public interface AnagramEntryService {

	/**
	 * Parses user input to extract anagrams and validates all words against given anagram pattern.
	 * If validation passes, anagrams are forwarded to repository to be saved in the database.
	 * @param userInput user query from the web form
	 * @param userAnagramsString user's words (anagrams) typed in the web page form
	 * @return set of unique words; every single returned word is an anagram to each
	 *			other in the returned set; set is to be exposed on the web page
	 */
	public Set<String> extractAndSaveAnagrams(String userInput, String userAnagramsString);
	
}
