package com.marcin.anagramator.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class mapping simple POJO to the table in the relational database.
 * An anagram is a word formed by rearranging the letters of a different word, 
 * using all the original letters exactly once. 
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
@Entity
@Table(name="anagram")
public class Anagram {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * A single word that is an anagram for a given sequence of letters.
	 */
	@Column(name="anagram_word")
	private String anagramWord;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="alphabetized_id")
	private Alphabetized alphabetized;
		
	public Anagram() {
	}

	public Anagram(String anagramWord) {
		this.anagramWord = anagramWord;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnagramWord() {
		return anagramWord;
	}

	public void setAnagramWord(String anagramWord) {
		this.anagramWord = anagramWord;
	}

	public Alphabetized getAlphabetized() {
		return alphabetized;
	}

	public void setAlphabetized(Alphabetized alphabetized) {
		this.alphabetized = alphabetized;
	}

	@Override
	public String toString() {
		return anagramWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alphabetized == null) ? 0 : alphabetized.hashCode());
		result = prime * result + ((anagramWord == null) ? 0 : anagramWord.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Anagram other = (Anagram) obj;
		return anagramWord.equals(other.anagramWord);
	}
}	