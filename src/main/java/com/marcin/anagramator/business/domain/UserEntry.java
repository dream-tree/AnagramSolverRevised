package com.marcin.anagramator.business.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a data holder for user input - 
 * list of anagrams as a single string.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Component
public class UserEntry {

	/**
	 * Serves as an input data holder for a single string of anagrams 
	 * to be added to the database after the process of validation.
	 * User input is splitted into separate anagrams and validated in the further process.
	 */
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,}(\\s+[a-zA-Z]{3,})*", 
				message="Only three or more letters words allowed separated by white space. Only letters allowed. ")
	private String stringOfAnagrams;
	
	public UserEntry() {
	}

	public String getStringOfAnagrams() {
		return stringOfAnagrams;
	}

	public void setStringOfAnagrams(String stringOfAnagrams) {
		this.stringOfAnagrams = stringOfAnagrams;
	}

	@Override
	public String toString() {
		return "UserEntry [stringOfAnagrams=" + stringOfAnagrams + "]";
	}
}