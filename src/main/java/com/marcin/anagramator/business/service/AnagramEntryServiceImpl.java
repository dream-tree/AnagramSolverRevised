package com.marcin.anagramator.business.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcin.anagramator.repository.AnagramListDAO;

/**
 * Provides the service for creating and validating anagrams from user 
 * input to allow persisting them in the database. 
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Service
public class AnagramEntryServiceImpl implements AnagramEntryService {

	private AnagramListDAO anagramsListDAO;
	
	@Autowired
	public AnagramEntryServiceImpl(AnagramListDAO anagramsListDAO) {
		this.anagramsListDAO = anagramsListDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getAnagramsFromUserInput(String userAnagramsString) {		
		Map<String, Set<String>> splitted = splitAndValidateEntry(userAnagramsString);	
		String userAlphabetized = "";
		Set<String> userAnagrams = new HashSet<>();
		for(String s : splitted.keySet()) {
			userAlphabetized = s;
			userAnagrams = splitted.get(s);
		}
		if(userAlphabetized.equals("error")
			// final confirmation whether entry exists in the database 
			|| anagramsListDAO.getAlphabetized(userAlphabetized).getAnagrams()!=null) {
			userAnagrams.clear();
			return userAnagrams;
		} else {
			anagramsListDAO.saveAnagramsList(userAlphabetized, userAnagrams);
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
	 * 			the multi-map key contains an "error" string for additional processing
	 */
	private Map<String, Set<String>> splitAndValidateEntry(String anagramsString) {
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
	 * Alphabetizes every single word and converts it to a lower case.
	 * Alphabetizing means that all chars of a given word are being sorted lexicographically.
	 * New string created in that way is called an alphabetized word.
	 * @param s "real" word to be alphabetized
	 * @return an alphabetized word (a "non-real" word)
	 */
	private static String alphabetize(String s) {
		char[] a = s.toLowerCase().toCharArray();
		Arrays.sort(a);
		return new String(a);
	}	
}
