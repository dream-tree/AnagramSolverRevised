package com.marcin.anagramator.business.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a data holder for user input, i.e. the list of anagrams as a single string.
 * List of anagrams is to be added to the database after the process of validation.
 * User input is splitted into separate anagrams and validated in the further process.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Component
public class UserEntry {

	/**
	 * User input data holder.	
	 */
	@NotNull(message="{user.null.message}")
	@Pattern(regexp="[a-zA-Z]{3,}(\\s+[a-zA-Z]{3,})*", message="{userEntry.message}")
	private String userWords;
		
	public UserEntry() {
	}

	public String getUserWords() {
		return userWords;
	}

	public void setUserWords(String userWords) {
		this.userWords = userWords;
	}

	@Override
	public String toString() {
		return "UserEntry [stringOfAnagrams=" + userWords + "]";
	}
}