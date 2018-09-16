package com.marcin.anagramator.business.service;

import java.util.List;

/**
 * Provides the service for deleting anagrams from the database.
 * All queries should interact with the database through this interface
 * rather than directly through the repository interface.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
public interface AlphabetizedService {

	/**
	 * Updates database entry when user selects specified anagrams for deletion.
	 * 
	 * @param sequence user query from the web form
	 * @param wordsForDeleting words (anagrams) selected by user to be removed from the database
	 */
	public void updateAlphabetized(String sequence, List<String> wordsForDeleting);

}
