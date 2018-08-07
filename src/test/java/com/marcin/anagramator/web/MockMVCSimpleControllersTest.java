package com.marcin.anagramator.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc 
@SpringBootTest
public class MockMVCSimpleControllersTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testMainControler() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("mainMenu"));
	}
	
	@Test
	public void testUserEntryController1() throws Exception {
		mockMvc.perform(get("/processWordForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("addForm"));
	}

	@Test
	public void testUserEntryController2() throws Exception {
		mockMvc.perform(get("/validateNewEntry"))
		.andExpect(status().isOk())
		.andExpect(view().name("addForm"));
	}
		
	@Test
	public void testUserQueryResultController1() throws Exception {
		mockMvc.perform(get("/showForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("inputForm"));
	}

	@Test
	public void testUserQueryResultController2() throws Exception {
		mockMvc.perform(get("/processForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("inputForm"));
	}
}