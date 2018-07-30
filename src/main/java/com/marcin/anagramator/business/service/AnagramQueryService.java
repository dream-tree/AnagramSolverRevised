package com.marcin.anagramator.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcin.anagramator.business.domain.UserQuery;

/**
 * Provides the service for fetching anagrams from the database 
 * based on the user web page query. 
 * All queries should interact with the database through this interface
 * rather than directly through the repository interface.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Service
public interface AnagramQueryService {

	public List<String> findAnagramsForUserQuery(UserQuery userQuery);
	
}
