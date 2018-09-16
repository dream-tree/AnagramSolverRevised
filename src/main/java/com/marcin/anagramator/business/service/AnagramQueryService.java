package com.marcin.anagramator.business.service;

import java.util.List;

import com.marcin.anagramator.business.domain.UserQuery;

/**
 * Provides the service for fetching anagrams from the database 
 * based on the user query from the web page. 
 * All queries should interact with the database through this interface
 * rather than directly through the repository interface.
 * 
 * @author dream-tree
 * @version 4.00, June-September 20188
 */
public interface AnagramQueryService {

	/**
	 * Parses user input to make available fetching the corresponding anagrams from the database.
	 * 
	 * @param userQuery
	 *			user query typed in the web page form to find appropriate anagrams 
	 * @return list of unique words fetched from the database
	 */
	public List<String> findAnagrams(UserQuery userQuery);
	
}
