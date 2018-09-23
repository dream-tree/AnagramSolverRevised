package com.marcin.anagramator.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.marcin.anagramator.business.domain.UserEntry;
import com.marcin.anagramator.business.service.AnagramEntryServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserEntryController.class)
public class UserEntryControllerUnitMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnagramEntryServiceImpl service;    
    @MockBean
    private UserEntry userEntry;
    @InjectMocks
    private UserEntryController controller;    
    @MockBean
    private Model model;
    @MockBean
    private BindingResult theBindingResult;   
 
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
		
	@Test
	public void validateNewEntryShouldReturnResultsNewEntryJspPage() throws Exception {
			
		when(service.extractAndSaveAnagrams(any(String.class), any(String.class))).thenReturn(new HashSet<String>());
		String userInput = "aabb";
		mockMvc
			.perform(get("/validateNewEntry").param("userInput", userInput))
			.andExpect(status().isOk())
			.andReturn();

		String outcome = controller.validateNewEntry(userInput, model, userEntry, theBindingResult);
		assertThat(outcome, is(equalTo("results-new-entry")));
	}	
}