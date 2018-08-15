package com.marcin.anagramator.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class ControllersUnitMockTest {

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
	public void processFormShouldReturnResultsNewEntryView() throws Exception {

		String mockUserSetOfAnagrams = "zzzaaqq qqaazzz";
		Set<String> mockUserAnagrams = new HashSet<String>() {{ add("zzzaaqq"); add("qqaazzz"); }};
			
		when(userEntry.getStringOfAnagrams()).thenReturn(mockUserSetOfAnagrams);
		when(service.extractAndSaveAnagrams(any(String.class))).thenReturn(mockUserAnagrams);
		
		mockMvc
			.perform(post("/validateNewEntry", model, userEntry, theBindingResult))
			.andExpect(status().isOk())
			.andReturn();

		String outcome = controller.processForm(model, userEntry, theBindingResult);
		assertThat(outcome, is(equalTo("resultsNewEntry")));
	}	
	
	@Test
	public void processFormShouldReturnResultErrorView() throws Exception {

		String mockUserSetOfAnagrams = "anagram noAnagramOfAnagram";
		Set<String> mockUserAnagrams = new HashSet<String>();
			
		when(userEntry.getStringOfAnagrams()).thenReturn(mockUserSetOfAnagrams);
		when(service.extractAndSaveAnagrams(any(String.class))).thenReturn(mockUserAnagrams);
		
		mockMvc
			.perform(post("/validateNewEntry", model, userEntry, theBindingResult))
			.andExpect(status().isOk())
			.andReturn();

		String outcome = controller.processForm(model, userEntry, theBindingResult);
		assertThat(outcome, is(equalTo("resultsError")));
	}
}