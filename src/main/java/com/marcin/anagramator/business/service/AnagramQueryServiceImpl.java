package com.marcin.anagramator.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcin.anagramator.business.domain.UserQuery;
import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;
import com.marcin.anagramator.repository.AnagramRepository;

/**
 * Provides the implementation of service for fetching anagrams from the database 
 * based on the user web page query. 
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Service
public class AnagramQueryServiceImpl implements AnagramQueryService {

	private AnagramRepository anagramsListDAO;
	
	@Autowired
	public AnagramQueryServiceImpl(AnagramRepository anagramsListDAO) {
		this.anagramsListDAO = anagramsListDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> findAnagramsForUserQuery(UserQuery userQuery) {
		String alphabetizedWord = alphabetize(userQuery.getUserSequeceOfLetters());   
		Alphabetized alphabetizedObject = anagramsListDAO.getAlphabetized(alphabetizedWord);	
		List<String> listOfFoundAnagrams = new ArrayList<>();
		if(alphabetizedObject.getAnagrams()==null)
			return listOfFoundAnagrams;
		listOfFoundAnagrams = alphabetizedObject.getAnagrams()
				.stream()
				.map(Anagram::getAnagramWord)
				.collect(Collectors.toList());		
		return listOfFoundAnagrams;
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
