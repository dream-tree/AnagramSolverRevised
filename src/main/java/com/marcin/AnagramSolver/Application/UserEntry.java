package com.marcin.AnagramSolver.Application;

import javax.validation.constraints.NotNull;

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
	private String optionalAnagramsString;
	
/*	*//**
	 * Serves as an input data holder for all anagrams typed by user
	 * to be displayed on the "result2.jsp" web page.
	 *//*
	private String[] optionalAnagrams;*/
	
	public UserEntry() {
	}

	
/*	public UserEntry(String[] optionalAnagrams, String optionalAnagramsString) {
		this.optionalAnagrams = optionalAnagrams;
		this.optionalAnagramsString = optionalAnagramsString;
	}*/

	public String getOptionalAnagramsString() {
		return optionalAnagramsString;
	}

	public void setOptionalAnagramsString(String optionalAnagramsString) {
		this.optionalAnagramsString = optionalAnagramsString;
	}

/*	public String[] getOptionalAnagrams() {
		return optionalAnagrams;
	}


	public void setOptionalAnagrams(String[] optionalAnagrams) {
		this.optionalAnagrams = optionalAnagrams;
	}*/


	@Override
	public String toString() {
		return "UserEntry [optionalAnagramsString=" + optionalAnagramsString/*
				+ ", optionalAnagrams=" + Arrays.toString(optionalAnagrams) */+ "]";
	}
}