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

import com.marcin.anagramator.business.domain.UserEntry;
import com.marcin.anagramator.business.service.AnagramEntryService;

/**
 * Controller handling the user operations for saving new anagrams in the database.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Controller
public class UserEntryController {
	
	private UserEntry theUserEntry;
	private AnagramEntryService anagramService;

	@Autowired
	public UserEntryController(UserEntry theUserEntry, AnagramEntryService anagramService) {
		this.theUserEntry = theUserEntry;
		this.anagramService = anagramService;
	}

	/**
	 * Adds the model for holding the list of anagrams corresponding to the user query.
	 * @param words model holding user query and all corresponding anagrams
	 */
	@RequestMapping("/processWordForm")
	public String processWordForm(Model entry) {  
		entry.addAttribute("theUserEntry", theUserEntry);  
		return "addForm";
	}

	/**
	 * Gets user input (string of anagrams), validates it and calls appropriate
	 * service for saving new entries in the database (after the successful validation)
	 * or returns an error page (if validation fails).
	 * @param theUserEntry object holding user data typed into a web page text field
	 * @param thebindingResult the holder for data binder
	 */
	@RequestMapping("/validateNewEntry")
	public String processForm(Model newAnagrams, @Valid @ModelAttribute("theUserEntry") UserEntry theUserEntry,
		BindingResult theBindingResult) {	
		if (theBindingResult.hasErrors()) {
			return "addForm";	
		}
		String userStringOfAnagrams = theUserEntry.getStringOfAnagrams();
		Set<String> userAnagrams = anagramService.extractAndSaveAnagrams(userStringOfAnagrams);	
		if(userAnagrams.isEmpty()) {
			return "resultsError";
		} else {
			newAnagrams.addAttribute("anagramsForDisplaying", userAnagrams);
			return "resultsNewEntry";
		}
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