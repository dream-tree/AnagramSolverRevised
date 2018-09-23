package com.marcin.anagramator.business.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.marcin.anagramator.repository.AlphabetizedRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class AnagramEntryServiceImplUnitTest {
	
	@Mock
	private AlphabetizedRepository repository;
		
	@InjectMocks
	private AnagramEntryServiceImpl service;
		
	private List<Anagram> mockListOfAnagrams;
	private Alphabetized aMockAlphabetized;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		Anagram anagram1 = new Anagram();
		anagram1.setAnagramWord("axxxzzz");
		Anagram anagram2 = new Anagram();
		anagram2.setAnagramWord("zzzaxxx");
		Anagram anagram3 = new Anagram();
		anagram3.setAnagramWord("xxxazzz");
		
		mockListOfAnagrams = new ArrayList<>();
		mockListOfAnagrams.add(anagram1);
		mockListOfAnagrams.add(anagram2);
		mockListOfAnagrams.add(anagram3);
		
		aMockAlphabetized = new Alphabetized();
		aMockAlphabetized.setAlphabetizedWord("axxxzzz");
		aMockAlphabetized.setAnagrams(mockListOfAnagrams);
	}
	
	@Test
	public void extractAndSaveAnagramsListShouldReturnNonEmptySetWithSingleAnagram() {					
		when(repository.saveAlphabetized(any(Alphabetized.class))).thenReturn(aMockAlphabetized);		
		Set<String> actual = service.extractAndSaveAnagrams("eden", "need");
		Set<String> expected = Set.of("need");		
		assertEquals(expected, actual);
	}
	
	@Test
	public void extractAndSaveAnagramsListShouldReturnNonEmptySet() {					
		when(repository.saveAlphabetized(any(Alphabetized.class))).thenReturn(aMockAlphabetized);			
		Set<String> newSetOfAnagrams = service.extractAndSaveAnagrams("xaxxzzz", "axxxzzz zzzaxxx xxxazzz");
		Set<String> fetched = aMockAlphabetized.getAnagrams()
				.stream()
				.map(anagram -> anagram.getAnagramWord())
				.collect(Collectors.toSet());	
		assertEquals(fetched, newSetOfAnagrams);
	}
	
	@Test
	public void extractAndSaveAnagramsListShouldReturnNonEmptySetAfterProperSplit() {					
		when(repository.saveAlphabetized(any(Alphabetized.class))).thenReturn(aMockAlphabetized);			
		Set<String> newSetOfAnagrams = service.extractAndSaveAnagrams("xaxxzzz", "axxxzzz  zzzaxxx    xxxazzz");
		Set<String> fetched = aMockAlphabetized.getAnagrams()
				.stream()
				.map(anagram -> anagram.getAnagramWord())
				.collect(Collectors.toSet());		
		assertEquals(fetched, newSetOfAnagrams);
	}
	
	@Test
	public void extractAndSaveAnagramsListShouldReturnEmptySet() {								
		Set<String> newSetOfAnagrams = service.extractAndSaveAnagrams("zzz", "axxxzzz zzzaxxx xxxazzz");
		Set<String> fetched = new HashSet<>();				
		assertEquals(fetched, newSetOfAnagrams);
	}
}