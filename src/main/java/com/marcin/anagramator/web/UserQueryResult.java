package com.marcin.anagramator.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a data holder for user input (query from a web form) 
 * and the corresponding anagrams fetched from the database.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Component
public class UserQueryResult {

	/**
	 * User input data holder.
	 */
	@NotNull
	@Pattern(regexp = "[a-zA-Z]{3,}", message="Only letters allowed (3 or more).")
	private String query;
	
	/**
	 * User query result data holder.
	 * Used to expose reference data to a web view through the accessor method.
	 */
	private String[] resultSet;
	
	public UserQueryResult() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getResultSet() {
		return resultSet;
	}

	public void setResultSet(String[] resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public String toString() {
		return "[Query: " + query + "]";
	}	
}