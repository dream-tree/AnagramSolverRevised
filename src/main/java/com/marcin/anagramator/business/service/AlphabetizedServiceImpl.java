package com.marcin.anagramator.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;
import com.marcin.anagramator.repository.AlphabetizedRepository;

/**
 * Provides the implementation of service for updating the database if user choose to delete some anagrams.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Service
public class AlphabetizedServiceImpl implements AlphabetizedService {

	private AlphabetizedRepository alphabetizedRepository;
	
	@Autowired
	public AlphabetizedServiceImpl(AlphabetizedRepository alphabetizedRepository) {
		this.alphabetizedRepository = alphabetizedRepository;
	}
		
	/** 
	 * Updates database entry when user selects specified anagrams for deletion.
	 * Updating process has 1 or 2 phases:
	 * <ol>
	 * <li>removing the existing object (Alphabetized and corresponding list of Anagrams) from the database</li>
	 * <li>persisting a new object (Alphabetized and corresponding list of Anagrams) in the database - optionally.</li>
	 * </ol>
	 * Phase two does not occur if user choose to delete all anagrams for a corresponding alphabetizedWord.
	 * 
	 * @param sequence user query from the web form
	 * @param wordsForDeleting words (anagrams) selected by user to be removed from the database
	 */
	@Override
	public void updateAlphabetized(String sequence, List<String> wordsForDeleting) {
		String alphabetizedWord = alphabetize(sequence);
		Alphabetized alphabetized = alphabetizedRepository.getAlphabetized(alphabetizedWord);
		alphabetizedRepository.deleteAlphabetized(alphabetizedWord);
		
		List<String> stringList = fetchWordsForSaving(alphabetized, wordsForDeleting);		
		// executed only if user didn't delete all anagrams for a given alphabetizedWord
		if(stringList.size()!=0) {
			Alphabetized alphabetizedForSaving = constructAlphabetized(alphabetizedWord, stringList);
			alphabetizedRepository.saveAlphabetized(alphabetizedForSaving);
		}
	}	
	
	/** 
	 * Converts the list Anagram objects to the list of of String objects 
	 * and removes from these list Strings (words) selected by user for deletion.
	 * 
	 * @param alphabetized Alphabetized object fetched from the database with the all corresponding Anagram objects
	 * @param wordsForDeleting words (anagrams) selected by user to be removed from the database
	 * @return list of words (anagrams) left for saving in the database (result list might be empty)
	 */
	public List<String> fetchWordsForSaving(Alphabetized alphabetized, List<String> wordsForDeleting) {
		List<Anagram> anagramList = alphabetized.getAnagrams();
		List<String> stringList = anagramList.stream()
				.map(anagram -> anagram.getAnagramWord())
				.collect(Collectors.toList());
		stringList.removeAll(wordsForDeleting);
		return stringList;
	}
		
	/** 
	 * Constructs new Alphabetized object and the corresponding Anagram objects for saving them in the database.
	 * 
	 * @param alphabetizedWord user query sorted lexicographically
	 * @param stringList list of words (anagrams) for saving in the database
	 * @return Alphabetized object with the all corresponding Anagram objects to be saved in the database
	 */
	public Alphabetized constructAlphabetized(String alphabetizedWord, List<String> stringList) {
		Alphabetized alphabetizedForSaving = new Alphabetized();
		alphabetizedForSaving.setAlphabetizedWord(alphabetizedWord);
		List<Anagram> anagramsForSaving = new ArrayList<>();
		for (String s : stringList) {
			Anagram anagram = new Anagram();
			anagram.setAnagramWord(s);
			anagram.setAlphabetized(alphabetizedForSaving);
			anagramsForSaving.add(anagram);
		}
		alphabetizedForSaving.setAnagrams(anagramsForSaving);
		return alphabetizedForSaving;
	}
	
	/**
	 * Alphabetizes every single word and converts it to lower case.
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
