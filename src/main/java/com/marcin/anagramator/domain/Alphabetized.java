package com.marcin.anagramator.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class mapping simple POJO to the table in the relational database.
 * Each instance of the class holds a keyword called {@link #alphabetizedWord} 
 * relating to the list of strings or, in the other words - the list of anagrams {@link #anagrams}.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Entity
@Table(name="alphabetized")
public class Alphabetized {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * Points if a given alphabetized word is an original database entry 
	 * or if it is added by the user.
	 * No original database entry (alphabetized word) can be deleted.
	 */
	@Column(name="alphabetized_marker")
	private int alphabetizedMarker;
	
	/**
	 * An alphabetized word (key).
	 * Alphabetized means that all chars of a given word are lexicographically sorted.
	 * New string created in that way is called an alphabetized word.
	 * <ul>
	 * <li>0 means an original database entry</li>
	 * <li>1 means a database entry added by user</li>
	 * </ul>
	 */
	@Column(name="alphabetized_word")
	private String alphabetizedWord;
	
	/**
	 * String of anagrams related to a given alphabetized word.
	 */
	@OneToMany(mappedBy="alphabetized", cascade=CascadeType.ALL)
	private List<Anagram> anagrams;
		
	public Alphabetized() {
	}

	public Alphabetized(int alphabetizedMarker, String alphabetizedWord) {
		this.alphabetizedMarker = alphabetizedMarker;
		this.alphabetizedWord = alphabetizedWord;
	}

	/**
	 * Adds all matching anagrams to the list. 
	 * If no list exists, it creates a new one.
	 * @param tempAnagram anagram word to be added to the list of anagrams
	 */
	public void add(Anagram tempAnagram) {                 
		if(anagrams == null) {
			anagrams = new ArrayList<>();
		}
		anagrams.add(tempAnagram);
		tempAnagram.setAlphabetized(this);           
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlphabetizedMarker() {
		return alphabetizedMarker;
	}

	public void setAlphabetizedMarker(int alphabetizedMarker) {
		this.alphabetizedMarker = alphabetizedMarker;
	}

	public String getAlphabetizedWord() {
		return alphabetizedWord;
	}

	public void setAlphabetizedWord(String alphabetizedWord) {
		this.alphabetizedWord = alphabetizedWord;
	}

	public List<Anagram> getAnagrams() {
		return anagrams;
	}

	public void setAnagrams(List<Anagram> anagrams) {
		this.anagrams = anagrams;
	}

/*	@Override
	public String toString() {
		return "Alphabetized [id=" + id + ", alphabetizedMarker=" + alphabetizedMarker + ", alphabetizedWord="
				+ alphabetizedWord + ", anagrams=" + anagrams + "]";
	}*/
}	