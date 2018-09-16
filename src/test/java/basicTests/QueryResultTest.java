/*package basicTests;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.marcin.anagramSolver.Application.QueryResult;
import com.marcin.anagramSolver.Application.UserQueryController;

import junit.framework.Assert;

public class QueryResultTest {

	@Mock
	UserQueryResultController userQueryController;
//	@Autowired
	
	@Mock
	BindingResult thebindingResult;
	
	@Test
	public void shouldValidateInput() {
		UserQueryResult queryResult = new UserQueryResult();
		queryResult.setQuery("we");
		System.err.println(queryResult.getQuery());
		System.err.println(queryResult);
		System.err.println(userQueryController);
		BindingResult result = mock(BindingResult.class);
		String answer = userQueryController.processForm(queryResult, thebindingResult);
		System.err.println("answer: " + answer);
		Assert.assertTrue(answer.equals("inputForm"));
	}
	
	@InitBinder                                                                             
	public void initBinder(WebDataBinder dataBinder) {
	     StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);   
	     dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);                
	}   
	
}
*/