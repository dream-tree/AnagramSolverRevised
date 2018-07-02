package com.marcin.AnagramSolver.DatabaseSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import com.marcin.AnagramSolver.Application.AnagramsListDAO;
import com.marcin.AnagramSolver.Application.QueryResult;
import com.marcin.AnagramSolver.Application.UserQueryController;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-servlet-context.xml")
public class AnotherTest {

    @Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
    @Before
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	@Autowired
	private WebApplicationContext context;
	
	@Autowired 
	private AnagramsListDAO anagramsListDAO;

/*	@BeforeClass
	public static void setUp() {		
		SessionFactory factory = HibernateUtility.getInstance();		
		session = factory.getCurrentSession();
		session.beginTransaction();
	}*/
	
	@Autowired
	private QueryResult queryResult;
	@Autowired
	private UserQueryController controller;
	
	@Test
	
	public void process() {
		System.err.println(queryResult);
		System.err.println(controller);
		System.err.println(anagramsListDAO);
		
		queryResult = new QueryResult();
		queryResult.setQuery("aedr");
		BindingResult thebindingResult = new BeanPropertyBindingResult(queryResult, "queryResult");
		UserQueryController controller = new UserQueryController(queryResult, anagramsListDAO);
		controller.processForm(queryResult, thebindingResult);
		Assert.assertEquals(controller.processForm(queryResult, thebindingResult), "inputForm");
	}
	
	
}
