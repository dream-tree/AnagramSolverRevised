package com.marcin.anagramator.business.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * Class serves as a data holder for user input, i.e. query from the web page.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Component
public class UserQuery {

	/**
	 * User query data holder.
	 */
	@NotNull(message="{user.null.message}")
	@Pattern(regexp="[a-zA-Z]{3,}", message="{userQuery.message}")
	private String userSequeceOfLetters;
	
	/**
	 * User words selected for deleting from the databases.
	 */
	private List<String> wordsForDeleting;
	
	public UserQuery() {
	}

	public String getUserSequeceOfLetters() {
		return userSequeceOfLetters;
	}

	public void setUserSequeceOfLetters(String userSequeceOfLetters) {
		this.userSequeceOfLetters = userSequeceOfLetters;
	}

	public List<String> getWordsForDeleting() {
		return wordsForDeleting;
	}

	public void setWordsForDeleting(List<String> wordsForDeleting) {
		this.wordsForDeleting = wordsForDeleting;
	}

	@Override
	public String toString() {
		return "[UserSequeceOfLetters: " + userSequeceOfLetters + "]";
	}	
}