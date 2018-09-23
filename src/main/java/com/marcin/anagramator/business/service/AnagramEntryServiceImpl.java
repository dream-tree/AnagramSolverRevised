package com.marcin.anagramator.business.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;
import com.marcin.anagramator.repository.AlphabetizedRepository;

/**
 * Provides the implementation of service for creating and validating anagrams from user 
 * input to allow persisting them in the database. 
 * If user input passes the validation, the set of unique anagrams is going to be persisted in the database
 * and returned to be exposed on the user result web page.
 * If validation fails, returned set is empty, to point there is an error in the user input. 
 * In this case, database is not affected in any way. 
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Service
public class AnagramEntryServiceImpl implements AnagramEntryService {

	private AlphabetizedRepository alphabetizedRepository;
	
	@Autowired
	public AnagramEntryServiceImpl(AlphabetizedRepository alphabetizedRepository) {
		this.alphabetizedRepository = alphabetizedRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> extractAndSaveAnagrams(String userInput, String userWordsString) {
		String alphabetizedPattern = alphabetize(userInput);
		Set<String> splittedUserAnagramsString = splitAndValidateEntry(alphabetizedPattern, userWordsString);		
		if(!splittedUserAnagramsString.isEmpty()) {
			Alphabetized alphabetized = new Alphabetized(alphabetizedPattern);
			addAnagramsToAlphabetized(alphabetized, splittedUserAnagramsString);			
			alphabetizedRepository.saveAlphabetized(alphabetized);			
		} 
		return splittedUserAnagramsString;	
	}
	
	/**
	 * Splits input (string of anagrams) into the separate words, alphabetizes first
	 * array element and validates all typed words against this alphabetized word.
	 * Validating means that every single word must equals an alphabetized word
	 * after rearranging (sorting lexicographically) its letters, using all the
	 * original letters exactly once.
	 * 
	 * @param alphabetizedPattern
	 *            user query from the web page after the process of alphabetizing
	 * @param stringOfAnagramWords
	 *            a single string of anagrams for further processing
	 * @return result set of anagrams after process of validation; if validation
	 *         fails, result set is empty
	 */
	public Set<String> splitAndValidateEntry(String alphabetizedPattern, String stringOfAnagramWords) {
		String[] splitted = stringOfAnagramWords.split("\\s+");
		Set<String> matchingWords = new HashSet<String>();	
		for (int i = 0; i < splitted.length; i++) {
			if (alphabetizedPattern.equals(alphabetize(splitted[i]))) {
				matchingWords.add(splitted[i]);
			} else {
				matchingWords.clear();
				break;
			}
		}
		return matchingWords;
	}		
	
	/**
	 * Constructs the Anagram objects based on user anagram words,
	 * adds those Anagram objects to the list of Anagrams 
	 * and sets the list to the Alphabetized object.
	 * @param alphabetized Alphabetized object to be set with user anagrams
	 * @param splittedUserAnagramsString user anagram words to be added to the Alphabetized object
	 */
	public void addAnagramsToAlphabetized(Alphabetized alphabetized, Set<String> splittedUserAnagramsString) {
		for(String singleString : splittedUserAnagramsString) {
			Anagram anagram = new Anagram(singleString);
			alphabetized.add(anagram);
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
