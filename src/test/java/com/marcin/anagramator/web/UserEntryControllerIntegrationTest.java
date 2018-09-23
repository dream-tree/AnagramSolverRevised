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

@RunWith(SpringRunner.class)              
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserEntryControllerIntegrationTest {

	@Autowired
	private UserEntryController userEntryController;

	@Mock
	Model words;
	@Mock
	BindingResult theBindingResult;
    
	@Test
	public void userEntryControllerShouldReturnAddFormJspPage() throws Exception {
		String userInput = "read";
		String outcome = userEntryController.processWordForm(userInput, words);
		assertThat(outcome, is(equalTo("add-form")));
	}	
}