package com.marcin.anagramator.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MockMVCControllersTest {

	@Autowired
	private MockMvc mockMvc;
	@PersistenceContext
	EntityManagerFactory entityManagerFactory;

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