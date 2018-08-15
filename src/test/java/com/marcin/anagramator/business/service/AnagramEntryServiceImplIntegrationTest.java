package com.marcin.anagramator.business.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnagramEntryServiceImplIntegrationTest {

	@Autowired
	private AnagramEntryServiceImpl service;
	
	@Test
	public void shouldReturnResultOfFirstIfConditionalInSplitAndValidateEntryMethod() {
		Map<String, Set<String>> actual = service.splitAndValidateEntry("ab ba");
		Map<String, Set<String>> expected = Map.of("error", new HashSet<>());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnResultOfSecondIfConditionalInSplitAndValidateEntryMethod() {
		Map<String, Set<String>> actual = service.splitAndValidateEntry("track");
		Map<String, Set<String>> expected = Map.of("ackrt", new HashSet<String>() {{ add("track"); }});
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnResultOfElseConditionalInSplitAndValidateEntryMethod() {
		Map<String, Set<String>> actual = service.splitAndValidateEntry("abcd dcba aaaa");
		Set<String> testSet = new HashSet<String>() {{ add("abcd"); add("dcba"); }};
		Map<String, Set<String>> expected = Map.of("error", testSet);
		assertEquals(expected, actual);
	}
		
	@Test
	public void shouldReturnFinalResultOfIfElseConditionalInSplitAndValidateEntryMethod() {
		Map<String, Set<String>> actual = service.splitAndValidateEntry("abcd dcba cdba");
		Set<String> testSet = new HashSet<String>() {{ add("abcd"); add("dcba"); add("cdba"); }};
		Map<String, Set<String>> expected = Map.of("abcd", testSet);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnErrorInExtractAndSaveAnagramsMethodNotAnagramable() {
		Set<String> actual = service.extractAndSaveAnagrams("abcd dcbx");
		Set<String> expected = new HashSet<String>();
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnErrorInExtractAndSaveAnagramsMethodAlredyInDatabase() {
		Set<String> actual = service.extractAndSaveAnagrams("read dare");
		Set<String> expected = new HashSet<String>();
		assertEquals(expected, actual);
	}
}