package com.marcin.anagramator.business.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a data holder for user input (query from a web page).
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Component
public class UserQuery {

	/**
	 * User input data holder.
	 */
	@NotNull
	@Pattern(regexp = "[a-zA-Z]{3,}", message="Only letters allowed (3 or more).")
	private String userSequeceOfLetters;
	
	/**
	 * User query result data holder.
	 * Used to expose reference data to a web view through the accessor method.
	 */
	
	public UserQuery() {
	}

	public String getUserSequeceOfLetters() {
		return userSequeceOfLetters;
	}

	public void setUserSequeceOfLetters(String userSequeceOfLetters) {
		this.userSequeceOfLetters = userSequeceOfLetters;
	}

	@Override
	public String toString() {
		return "[UserSequeceOfLetters: " + userSequeceOfLetters + "]";
	}	
}