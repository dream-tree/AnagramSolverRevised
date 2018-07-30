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

import com.marcin.anagramator.business.domain.UserQuery;
import com.marcin.anagramator.business.service.AnagramQueryService;

/**
 * Controller handling the user query for finding anagrams in the database.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Controller
public class UserQueryController {
	
	private AnagramQueryService anagramQueryService;
	private UserQuery userQuery;
	
	@Autowired
	public UserQueryController(AnagramQueryService anagramQueryService, UserQuery userQuery) {
		this.anagramQueryService = anagramQueryService;
		this.userQuery = userQuery;
	}

	/**
	 * Adds the model for holding user query data and corresponding result list of anagrams.
	 * @param words model holding user query and corresponding result data
	 */
	@RequestMapping("/showForm")
	public String showForm(Model words) {   
		words.addAttribute("theQuery", userQuery);
		return "inputForm";
	}

	/**
	 * Validates user input (query) for anagrams, processes it and sets result list of anagrams.
	 * @param userQuery object holding user query data and corresponding result list of anagrams
	 * @param theBindingResult the holder for data binder 
	 */
	@RequestMapping(value="/processForm")
	public String processForm(Model words, @Valid @ModelAttribute("theQuery") UserQuery userQuery, 
			BindingResult theBindingResult) {		
		if (theBindingResult.hasErrors())
			return "inputForm";
		List<String> listOfAnagrams = anagramQueryService.findAnagramsForUserQuery(userQuery);
		if(listOfAnagrams.isEmpty())
			return "noResultAndAskForm";
		words.addAttribute("theListOfAnagrams", listOfAnagrams);
		return "results";		
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