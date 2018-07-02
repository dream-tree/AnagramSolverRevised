package com.marcin.AnagramSolver.Application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for the top-level web requests mapping.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Controller
public class MainController {
	
	@RequestMapping("/") 
	public String showMainMenu() {
		return "mainMenu";
	}
}