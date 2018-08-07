package com.marcin.anagramator.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcin.anagramator.web.MainController;
import com.marcin.anagramator.web.UserEntryController;
import com.marcin.anagramator.web.UserQueryController;

@RunWith(SpringRunner.class)              
@SpringBootTest
public class SimpleControllersTest {

    @Autowired
    private MainController mainControler;
    @Autowired
    private UserEntryController userEntryController;
    @Autowired
    private UserQueryController userQueryController;

	@Test
	public void testCreatingControllers() throws Exception {
		assertThat(mainControler).isNotNull();		
		assertThat(userQueryController).isNotNull();
		assertThat(userEntryController).isNotNull();
	}	
}