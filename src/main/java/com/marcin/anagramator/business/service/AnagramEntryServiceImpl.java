package com.marcin.anagramator.business.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;
import com.marcin.anagramator.repository.AnagramRepository;

/**
 * Provides the implementation of service for creating and validating anagrams from user 
 * input to allow persisting them in the database. 
 * If user input passes the validation, the set of unique anagrams is going to be persisted in the database
 * and returned to be exposed on the user result web page.
 * If validation fails, returned set is empty, to point there is an error in the user input. 
 * In this case, database is not affected in any way. 
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Service
public class AnagramEntryServiceImpl implements AnagramEntryService {

	private AnagramRepository anagramsListDAO;
	
	@Autowired
	public AnagramEntryServiceImpl(AnagramRepository anagramsListDAO) {
		this.anagramsListDAO = anagramsListDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	// TODO simplify it
	public Set<String> extractAndSaveAnagrams(String userAnagramsString) {		
		Map<String, Set<String>> splitted = splitAndValidateEntry(userAnagramsString);	
		String userAlphabetized = "";
		Set<String> userAnagrams = new HashSet<>();
		for(String s : splitted.keySet()) {
			userAlphabetized = s;
			userAnagrams = splitted.get(s);
		}		
		if(userAlphabetized.equals("error")
			/* final confirmation whether entry exists in the database */
			|| anagramsListDAO.getAnagrams(userAlphabetized).getAnagrams()!=null) {
			userAnagrams.clear();
			return userAnagrams;
		} else {
			Alphabetized userAlphabetizedObject = new Alphabetized(1, userAlphabetized);
			addAnagramsToAlphabetizedObject(userAlphabetizedObject, userAnagrams);			
			anagramsListDAO.saveAnagrams(userAlphabetizedObject);
			return userAnagrams;
		}		
	}
	
	/**
	 * Splits input (string of anagrams) into the separate words, alphabetizes first array element 
	 * and validates all typed words against this alphabetized word.
	 * Validating means that every single word must equals an alphabetized word after rearranging
	 * (sorting lexicographically) its letters, using all the original letters exactly once. 
	 * @param anagramsString a single string of anagrams for further processing
	 * @return a result multi-map of an alphabetized word and the corresponding list of anagrams;
	 * 			if any word does not match the alphabetized word, 
	 * 			the multi-map key contains an "error" flag for additional processing
	 */
	public Map<String, Set<String>> splitAndValidateEntry(String anagramsString) {
		String[] splitted = anagramsString.split("\\s+");
		String alphabetizedPattern = alphabetize(splitted[0]);
		Set<String> matchingWords = new HashSet<String>();
		if(splitted[0].length() < 3) {
			return Map.of("error", matchingWords);   // Java 9 only
		}
		if(splitted.length==1) {
			Set<String> singleWord = Collections.singleton(splitted[0]);	
			return Map.of(alphabetizedPattern, singleWord); 
		} else {		
			for(int i = 0; i < splitted.length; i++) {
				if(alphabetizedPattern.equals(alphabetize(splitted[i]))) {
					matchingWords.add(splitted[i]);
					continue;
				} else {
					return Map.of("error", matchingWords);
				}
			}
		}
		return Map.of(alphabetizedPattern, matchingWords);	
	}	
	
	/**
	 * Constructs the Anagram objects based on user anagram words,
	 * adds those Anagram objects to the list of Anagrams 
	 * and sets the list to the Alphabetized object.
	 * @param userAlphabetizedObject Alphabetized object to be set with user anagrams
	 * @param userAnagrams user anagrams to be added to the Alphabetized object
	 */
	public void addAnagramsToAlphabetizedObject(Alphabetized userAlphabetizedObject, Set<String> userAnagrams) {
		for(String singleString : userAnagrams) {
			Anagram userSingleAnagram = new Anagram(1, singleString);
			userAlphabetizedObject.add(userSingleAnagram);
		}
	}	
	
	/**
	 * Alphabetizes every single word and converts it to a lower case.
	 * Alphabetizing means that all chars of a given word are being sorted lexicographically.
	 * New string created in that way is called an alphabetized word.
	 * @param s "real" word to be alphabetized
	 * @return an alphabetized word (a "non-real" word)
	 */
	public static String alphabetize(String s) {
		char[] a = s.toLowerCase().toCharArray();
		Arrays.sort(a);
		return new String(a);
	}	
}
