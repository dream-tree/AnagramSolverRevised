package com.marcin.anagramator.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marcin.anagramator.business.domain.UserQuery;
import com.marcin.anagramator.business.service.AlphabetizedService;
import com.marcin.anagramator.business.service.AnagramQueryService;

/**
 * Controller handling the user query for finding anagrams in the database.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Controller
public class UserQueryController {
	
	private AnagramQueryService anagramQueryService;
	private AlphabetizedService alphabetizedService;
	private UserQuery userQuery;
	
	@Autowired
	public UserQueryController(AnagramQueryService anagramQueryService, AlphabetizedService alphabetizedService, 
			UserQuery userQuery) {
		this.anagramQueryService = anagramQueryService;
		this.alphabetizedService = alphabetizedService;
		this.userQuery = userQuery;
	}

	/**
	 * Adds the model for holding user query data and corresponding result list of anagrams.
	 * @param words model holding user query and corresponding result data
	 */
	@RequestMapping("/showForm")
	public String showForm(Model words) {   
		words.addAttribute("theQuery", userQuery);
		return "input-form";
	}

	/**
	 * Validates user input (query) for anagrams, processes it and sets result list of anagrams.
	 * @param model model holding all anagrams for a given query and anagrams for deletion
	 * @param userQuery object holding user query data
	 * @param theBindingResult the holder for data binder 
	 */
	@RequestMapping(value="/processForm")
	public String processForm(Model words, @Valid @ModelAttribute("theQuery") UserQuery userQuery, 
			BindingResult theBindingResult) {		
		if (theBindingResult.hasErrors())
			return "input-form";
		List<String> listOfAnagrams = anagramQueryService.findAnagrams(userQuery);
		if(listOfAnagrams.isEmpty())
			return "no-result-and-ask-form";
		words.addAttribute("theListOfAnagrams", listOfAnagrams);
		words.addAttribute("anArrayForDeleting", new UserQuery());
		return "results";		
	}
		
	/**
	 * Enables the process of deletion of the selected anagrams.
	 * @param model model holding all anagrams for a given query and anagrams for deletion
	 * @param userQuery object holding user query data
	 * @param theSequence user query typed in the web page form 
	 */
	@RequestMapping("/deleteEntries")
	public String deleteEntries(@ModelAttribute("anArrayForDeleting") UserQuery theUserQuery, 
			@RequestParam("sequence") String theSequence) {
		List<String> wordsForDeleting = theUserQuery.getWordsForDeleting();	
		alphabetizedService.updateAlphabetized(theSequence, wordsForDeleting);		
		return "delete-results";
	}
	
	/**
	 * Supports validation on non-empty input in the search bar.
	 * @param dataBinder SpringFramewrok WebDataBinder object
	 */
	@InitBinder                                                                             
	public void initBinder(WebDataBinder dataBinder) {
	     StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);   
	     dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);                
	}     
}