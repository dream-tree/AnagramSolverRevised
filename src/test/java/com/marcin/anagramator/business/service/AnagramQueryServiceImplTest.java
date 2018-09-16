package com.marcin.anagramator.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcin.anagramator.business.domain.UserQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnagramQueryServiceImplTest {

	@Autowired
	private AnagramQueryServiceImpl service;
	
	@Test
	public void findFindAnagramsForUserQueryShouldReturnResultList() {
		UserQuery sampleUserQuery = new UserQuery();
		sampleUserQuery.setUserSequeceOfLetters("read");
		List<String> actual = service.findAnagrams(sampleUserQuery);
		List<String> expected = new ArrayList<String>() {{ add("dare"); add("dear"); add("read");}};
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void findAnagramsForUserQueryShouldReturnNoResult() {
		UserQuery sampleUserQuery = new UserQuery();
		sampleUserQuery.setUserSequeceOfLetters("aaaa");
		List<String> actual = service.findAnagrams(sampleUserQuery);
		assertNotNull(actual);
		assertEquals(new ArrayList<String>(), actual);
	}
	
	@Test
	public void shouldReturnAlphabetizedWordForUserInput() {
		String alphabetizedWord = AnagramQueryServiceImpl.alphabetize("need");
		assertEquals("deen", alphabetizedWord);
	}
	
	@Test
	public void shouldReturnAlphabetizedWordForUserInputCaseInsensitive() {
		String alphabetizedWord = AnagramQueryServiceImpl.alphabetize("LEssIe");
		assertEquals("eeilss", alphabetizedWord);
	}
}
