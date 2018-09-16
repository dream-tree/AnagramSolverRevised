package com.marcin.anagramator.business.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;
import com.marcin.anagramator.repository.AnagramRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class AnagramEntryServiceImplUnitTest {
	
	@Mock
	private AnagramRepository repository;
		
	@InjectMocks
	private AnagramEntryServiceImpl service;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveAnagramsListShouldReturnOK() {
		
		Anagram anagram1 = new Anagram();
		anagram1.setAnagramMarker(1);
		anagram1.setAnagramWord("axxxzzz");
		Anagram anagram2 = new Anagram();
		anagram2.setAnagramMarker(1);
		anagram2.setAnagramWord("zzzaxxx");
		Anagram anagram3 = new Anagram();
		anagram3.setAnagramMarker(1);
		anagram3.setAnagramWord("xxxazzz");
		
		List<Anagram> mockListOfAnagrams = new ArrayList<>();
		mockListOfAnagrams.add(anagram1);
		mockListOfAnagrams.add(anagram2);
		mockListOfAnagrams.add(anagram3);
		
		Alphabetized aMockAlphabetized = new Alphabetized();
		aMockAlphabetized.setAlphabetizedWord("axxxzzz");
		aMockAlphabetized.setAnagrams(mockListOfAnagrams);
		
		when(repository.getAlphabetized(any(String.class))).thenReturn(new Alphabetized());		
		when(repository.saveAlphabetized(any(Alphabetized.class))).thenReturn(aMockAlphabetized);
		
		Set<String> newSetOfAnagrams = service.extractAndSaveAnagrams("axxxzzz zzzaxxx xxxazzz");
		Set<String> fetched = aMockAlphabetized.getAnagrams()
				.stream()
				.map(anagram -> anagram.getAnagramWord())
				.collect(Collectors.toSet());
		
		assertEquals(fetched, newSetOfAnagrams);
	}
}