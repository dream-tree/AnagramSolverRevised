package com.marcin.anagramator.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcin.anagramator.domain.Alphabetized;
import com.marcin.anagramator.domain.Anagram;

@RunWith(SpringRunner.class)              
@SpringBootTest
public class AlphabetizedRepositoryTest {
	
	@Autowired
	private AlphabetizedRepositoryImpl repository;
	
	@Test
	public void shouldGetRightAnagrams1() {
		
		String alphabetizedQuery = "aah";
		
		Alphabetized alpha = new Alphabetized();
		
		Anagram anagram1 = new Anagram();
		anagram1.setAnagramWord("aah");
		Anagram anagram2 = new Anagram();
		anagram2.setAnagramWord("aha");
		
		alpha.setAlphabetizedWord("aah");
		alpha.setId(1);		
		alpha.setAnagrams(new ArrayList<Anagram>() {{ add(anagram1); add(anagram2); }});
		
		assertEquals(alpha, repository.getAlphabetized(alphabetizedQuery)); 
	}
	
	@Test
	public void shouldGetRightAnagrams2() {
		
		String alphabetizedQuery = "aelpp";
		
		Alphabetized alpha = new Alphabetized();
		
		Anagram anagram1 = new Anagram();
		anagram1.setAnagramWord("appel");
		Anagram anagram2 = new Anagram();
		anagram2.setAnagramWord("apple");
		Anagram anagram3 = new Anagram();
		anagram3.setAnagramWord("pepla");
		
		alpha.setAlphabetizedWord("aelpp");
		alpha.setId(7112);		
		alpha.setAnagrams(new ArrayList<Anagram>() {{ add(anagram1); add(anagram2); add(anagram3); }});
		
		assertEquals(alpha, repository.getAlphabetized(alphabetizedQuery)); 
	}
	
		@Test
		public void shouldGetRightAnagrams3() {
		
		String alphabetizedQuery = "eenrt";
		
		Alphabetized alpha = new Alphabetized();
		
		Anagram anagram1 = new Anagram();
		anagram1.setAnagramWord("enter");
		Anagram anagram2 = new Anagram();
		anagram2.setAnagramWord("rente");
		Anagram anagram3 = new Anagram();
		anagram3.setAnagramWord("terne");
		Anagram anagram4 = new Anagram();
		anagram4.setAnagramWord("treen");		
		
		alpha.setAlphabetizedWord("eenrt");
		alpha.setId(47139);		
		alpha.setAnagrams(new ArrayList<Anagram>() {{ add(anagram1); add(anagram2); add(anagram3); add(anagram4); }});
		
		assertEquals(alpha, repository.getAlphabetized(alphabetizedQuery)); 
	}
}
			