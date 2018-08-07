package com.marcin.anagramator.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.marcin.anagramator.business.domain.UserEntry;
import com.marcin.anagramator.business.domain.UserQuery;

@RunWith(SpringRunner.class)              
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ControllersIntegrationTest {

	@Autowired
	private MainController mainControler;
	@Autowired
	private UserQueryController userQueryController;
	@Autowired
	private UserEntryController userEntryController;

	@Mock
	Model words;
	@Mock
	BindingResult theBindingResult;
    
	@Test
	public void mainControlerShouldReturnMainMenuJspPage() throws Exception {
		String outcome = mainControler.showMainMenu();
		assertThat(outcome, is(equalTo("mainMenu")));
	}	
		
	@Test
	public void userQueryControllerShouldReturnResultsJspPage() throws Exception {
		UserQuery userQuery = new UserQuery();
		userQuery.setUserSequeceOfLetters("good");
		String outcome = userQueryController.processForm(words, userQuery, theBindingResult);
		assertThat(outcome, is(equalTo("results")));
	}
	
	@Test
	public void userQueryControllerShouldReturnNoResultAndAskFormJspPage() throws Exception {
		UserQuery userQuery = new UserQuery();
		userQuery.setUserSequeceOfLetters("noSuchWordInDatabase");
		String outcome = userQueryController.processForm(words, userQuery, theBindingResult);
		assertThat(outcome, is(equalTo("noResultAndAskForm")));
	}
		
	@Test
	public void userEntryControllerShouldReturnNoResultAndAskFormJspPage() throws Exception {
		UserEntry userEntry = new UserEntry();
		userEntry.setStringOfAnagrams("goodOK goodNO");
		String outcome = userEntryController.processForm(words, userEntry, theBindingResult);
		assertThat(outcome, is(equalTo("resultsError")));
	}	
}