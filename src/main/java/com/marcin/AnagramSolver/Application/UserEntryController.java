package com.marcin.AnagramSolver.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controllers for the application.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Controller
public class UserEntryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEntryController.class);
	
	private UserEntry userEntry;
	private QueryResult queryResult;
	private AnagramsListDAO anagramsListDAO;
	
	@Autowired
	public UserEntryController(UserEntry userEntry, QueryResult queryResult,
			AnagramsListDAO anagramsListDAO) {
		this.userEntry = userEntry;
		this.queryResult = queryResult;
		this.anagramsListDAO = anagramsListDAO;
	}

	/**
	 * Adds the model for holding user query data and corresponding result data (anagrams).
	 * @param words model holding user query and corresponding result data
	 */
	@RequestMapping("/processWordForm")
	public String processWordForm(Model entry) {  
		entry.addAttribute("userNewEntry", userEntry);  
		return "addForm";
	}

	/**
	 * Gets user input (string of anagrams), processes it for appropriate validation
	 * and calls DAO method for saving new entries in the database (if validation passed)
	 * or returns an error page (if validation failed).
	 * @param userEntry object holding user data typed into a text field
	 * @param thebindingResult the holder for data binder // TODO
	 */
	@RequestMapping("/validateNewEntry")
	public String processForm(@Valid @ModelAttribute("userNewEntry") UserEntry userEntry, 
			BindingResult bindingResult) {	
		if(bindingResult.hasErrors()) {
			System.err.println("BINDING RESULTS ERROR!!!");
			return "addForm";
		}
		String userAnagramsString = userEntry.getOptionalAnagramsString();
		Map<String, List<String>> splitted = splitAndValidateEntry(userAnagramsString);		
		splitted.forEach((x, y) -> System.out.println(x + " " + y));		
		String userAlphabetized = "";
		List<String> userAnagrams = null;
		for (String s : splitted.keySet()) {
			userAlphabetized = s;
			userAnagrams = splitted.get(s);
		}
		if(userAlphabetized.equals("error") 
				// final confirmation: entry does not exist in the database 
				// (user might have changed sth browsing the pages to and forth)
				|| anagramsListDAO.getAnagramsList(userAlphabetized) != null ){
			return "resultsError";
		} else {
			anagramsListDAO.saveAnagramsList(userAlphabetized, userAnagrams);
			return "resultsNewEntry";
			}
	}
			
/*	*//**
	 * Supports validation on non-empty input in the search bar.
	 * @param dataBinder SpringFramewrok WebDataBinder object
	 */
	@InitBinder                                                                             
	public void initBinder(WebDataBinder dataBinder) {
	     StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);   
	     dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);                
	}     
	
	/**
	 * Splits input (string of anagrams) into separate words, alphabetizes first array element 
	 * and validates all typed words against this alphabetized word.
	 * Validating means that every single word must equals an alphabetized word after rearranging
	 * (sorting lexicographically) its letters, using all the original letters exactly once. 
	 * @param anagramsString a single string of anagrams for further processing
	 * @return a result multi-map of an alphabetized word and the corresponding list of anagrams;
	 * 			if any word does not match the alphabetized word, 
	 * 			the multi-map key contains an "error" string for additional processing
	 */
	private Map<String, List<String>> splitAndValidateEntry(String anagramsString) {
		String[] splitted = anagramsString.split("\\s+");
		String alphabetizedPattern = alphabetize(splitted[0]);
		List<String> matchingWords = new ArrayList<String>();
		if(splitted[0].length() < 3) {
			return Map.of("error", matchingWords);   // Java 9 only
		}
		if(splitted.length==1) {
			List<String> singleWord = Arrays.asList(splitted[0]);		
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
	 * Alphabetizes every single word and converts it to lower case.
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