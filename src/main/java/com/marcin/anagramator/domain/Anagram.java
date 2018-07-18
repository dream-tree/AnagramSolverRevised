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
 * Each object o this class holds a marker {@link #anagramMarker}
 * pointing if it is an original database entry or is added by user.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@Entity
@Table(name="anagram")
public class Anagram {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/**
	 * Points if a given anagram is an original database entry or is added by user.
	 * Original database entry cannot be deleted.
	 * <ul>
	 * <li>0 means an original database entry</li>
	 * <li>1 means a database entry added by user</li>
	 * </ul>
	 */
	@Column(name="anagram_marker")
	private int anagramMarker;
	
	/**
	 * An anagram string (a single word).
	 */
	@Column(name="anagram_word")
	private String anagramWord;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, 
			CascadeType.REFRESH})
	@JoinColumn(name="alphabetized_id")
	private Alphabetized alphabetized;
		
	public Anagram() {
	}

	public Anagram(int anagramMarker, String anagramWord) {
		this.anagramMarker = anagramMarker;
		this.anagramWord = anagramWord;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnagramMarker() {
		return anagramMarker;
	}

	public void setAnagramMarker(int anagramMarker) {
		this.anagramMarker = anagramMarker;
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

/*	@Override
	public String toString() {
		return "Anagram [id=" + id + ", anagramMarker=" + anagramMarker + ", anagramWord=" + anagramWord
				+ ", alphabetized=" + alphabetized + "]";
	}*/
}	