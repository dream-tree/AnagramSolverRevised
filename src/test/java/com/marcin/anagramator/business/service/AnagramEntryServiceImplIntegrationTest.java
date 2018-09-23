package com.marcin.anagramator.business.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
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
	public void shouldReturnSetOfAnagrams() {
		Set<String> actual = service.splitAndValidateEntry("ader", "read dear reda");
		Set<String> expected = Set.of("dear", "reda", "read");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnSetOfAnagramsAfterProperSplit() {
		Set<String> actual = service.splitAndValidateEntry("ader", "read      dear  reda");
		Set<String> expected = Set.of("dear", "reda", "read");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnSetWithSingleAnagram() {
		Set<String> actual = service.splitAndValidateEntry("ddehin", "hidden");
		Set<String> expected = Set.of("hidden");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnEmptySetWhenValidationFails() {
		Set<String> actual = service.splitAndValidateEntry("ader", "read dear reeda");
		Set<String> expected = new HashSet<>();
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnEmptySetWhenValidationFails2() {
		Set<String> actual = service.splitAndValidateEntry("ader", "aderader");
		Set<String> expected = new HashSet<>();
		assertEquals(expected, actual);
	}
}