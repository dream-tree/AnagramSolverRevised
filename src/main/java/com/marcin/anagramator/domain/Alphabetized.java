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
 * Each object of this class holds a keyword called an {@link #alphabetizedWord} 
 * relating to the list of strings or, in the other words - the list of {@link #anagrams}.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Entity
@Table(name="alphabetized")
public class Alphabetized {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * An alphabetized word (key).
	 * Alphabetized stands for all chars of a given word sorted lexicographically.
	 * New string created in that way is called an alphabetized word.
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

	public Alphabetized(String alphabetizedWord) {
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

	@Override
	public String toString() {
		return "Alphabetized [id=" + id + ", alphabetizedWord=" + alphabetizedWord + ", anagrams list: " + anagrams + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alphabetizedWord == null) ? 0 : alphabetizedWord.hashCode());
		result = prime * result + ((anagrams == null) ? 0 : anagrams.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alphabetized other = (Alphabetized) obj;
		if (alphabetizedWord == null) {
			if (other.alphabetizedWord != null)
				return false;
		} else if (!alphabetizedWord.equals(other.alphabetizedWord))
			return false;
		if (anagrams == null) {
			if (other.anagrams != null)
				return false;
		} else if (!anagrams.equals(other.anagrams))
			return false;
		if (id != other.id)
			return false;
		return true;
	}	
}	