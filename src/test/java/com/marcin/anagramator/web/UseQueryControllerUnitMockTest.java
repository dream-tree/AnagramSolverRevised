package com.marcin.anagramator.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.marcin.anagramator.business.domain.UserQuery;
import com.marcin.anagramator.business.service.AlphabetizedService;
import com.marcin.anagramator.business.service.AnagramQueryService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserQueryController.class)
public class UseQueryControllerUnitMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlphabetizedService alphabetizedService;      
    @MockBean
    private AnagramQueryService anagramQueryService;    
    @MockBean
    private UserQuery userQuery;
    @InjectMocks
    private UserQueryController controller;    
    @MockBean
    private Model model;
    @MockBean
    private BindingResult theBindingResult;   
 
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
		
	@Test
	public void deleteEntriesShouldReturnDeleteResultsJspPage() throws Exception {		
		doNothing().when(alphabetizedService).updateAlphabetized(any(String.class), anyList());
		String sequence = "aabb";
		mockMvc
			.perform(get("/deleteEntries").param("sequence", sequence))
			.andExpect(status().isOk())
			.andReturn();

		String outcome = controller.deleteEntries(userQuery, sequence);
		assertThat(outcome, is(equalTo("delete-results")));
	}	
}