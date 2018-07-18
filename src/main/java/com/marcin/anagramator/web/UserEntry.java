package com.marcin.anagramator.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a model for holding user input (query from a web form) 
 * and the corresponding result data (anagrams).
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Component
public class UserEntry {

	/**
	 * Serves as an input data holder for a single string of anagrams 
	 * to be added to the database after process of validation.
	 * User input is splitted into separate anagrams and validated in the further process.
	 */
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,}(\\s+[a-zA-Z]{3,})*", 
				message="Only letters allowed. Only three or more letters words allowed.")
	private String optionalAnagramsString;
	
	public UserEntry() {
	}

	public String getOptionalAnagramsString() {
		return optionalAnagramsString;
	}

	public void setOptionalAnagramsString(String optionalAnagramsString) {
		this.optionalAnagramsString = optionalAnagramsString;
	}

	@Override
	public String toString() {
		return "UserEntry [optionalAnagramsString=" + optionalAnagramsString + "]";
	}
}