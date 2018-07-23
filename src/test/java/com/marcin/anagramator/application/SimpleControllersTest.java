package com.marcin.anagramator.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcin.anagramator.web.MainController;
import com.marcin.anagramator.web.UserEntryController;
import com.marcin.anagramator.web.UserQueryResultController;

@RunWith(SpringRunner.class)              
@SpringBootTest
public class SimpleControllersTest {

    @Autowired
    private MainController mainControler;
    @Autowired
    private UserEntryController userEntryController;
    @Autowired
    private UserQueryResultController userQueryResultController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(mainControler).isNotNull();		
		assertThat(userQueryResultController).isNotNull();
		assertThat(userEntryController).isNotNull();
	}	
}