package com.marcin.anagramator.web;

import java.util.Set;

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

import com.marcin.anagramator.business.domain.UserEntry;
import com.marcin.anagramator.business.service.AnagramEntryService;

/**
 * Controller handling the user operations for saving new anagrams in the database.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Controller
public class UserEntryController {
	
	private UserEntry userEntry;
	private AnagramEntryService anagramService;

	@Autowired
	public UserEntryController(UserEntry theUserEntry, AnagramEntryService anagramService) {
		this.userEntry = theUserEntry;
		this.anagramService = anagramService;
	}

	/**
	 * Adds the model for holding the list of anagrams corresponding to the user query.
	 * @param userInput user query typed in the web page form 
	 * @param entry model holding user query and all corresponding anagrams
	 */
	@RequestMapping("/processWordForm")
	public String processWordForm(@RequestParam("userInput") String userInput, Model entry) {  
		entry.addAttribute("theUserEntry", userEntry);  
		entry.addAttribute("theUserInput", userInput);
		return "add-form";
	}

	/**
	 * Gets user input (string of anagrams), validates it and calls appropriate
	 * service for saving new entries in the database (after the successful validation)
	 * or returns an error page (if validation fails).
	 * @param userInput user query typed in the web page form 
	 * @param model model holding user query and all corresponding anagrams
	 * @param theUserEntry object holding user anagrams (words) typed into a web page text field
	 * @param thebindingResult the holder for data binder
	 */
	@RequestMapping("/validateNewEntry")
	public String validateNewEntry(@RequestParam("userInput") String userInput,  Model model, 
			@Valid @ModelAttribute("theUserEntry") UserEntry theUserEntry, BindingResult theBindingResult) {	
		model.addAttribute("theUserInput", userInput);
		if (theBindingResult.hasErrors()) {
			return "add-form";	
		}
		String userWordsString = theUserEntry.getUserWords();
		Set<String> validatedWords = anagramService.extractAndSaveAnagrams(userInput, userWordsString);	
		model.addAttribute("anagramsForDisplaying", validatedWords);
		return "results-new-entry";
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