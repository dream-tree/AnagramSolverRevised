package com.marcin.AnagramSolver.DatabaseSetup;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVReader;

import ch.qos.logback.classic.Logger;
/**
 * Class parses all dictionary words coming from the .csv file, 
 * alphabetizes every single word
 * and maps every single word to its alphabetized counterpart.
 * Results are stored in a multi-map.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public class WordParser {

	private static final Path PATH = FileSystems.getDefault().getPath("src/main/resources/word_list.csv");
	private static Logger logger;

	public WordParser() {
	}
	
	/*
	 * (non-Javadoc) 
	 * Used only for exercising purposes; 
	 * method result: 173.530 list elements 
	 */
	public static List<String> readWords1() {                    
		List<String> list = new ArrayList<>();
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(PATH.toString()));
			String[] line;
			while ((line = reader.readNext()) != null) {
				list.add(line[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
		return list;
	}
	
	/*
	 * (non-Javadoc) 
	 * Used only for exercising purposes; 
	 * method result: 157.079 map entries (all unique alphabetized words)
	 * method result: 157.004 map entries after the removal of 1 and 2-letter words
	 */
	public static Map<String, List<String>> readWords2() {															
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(PATH.toString()));
			while (scanner.hasNext()) {
				String word = scanner.next();
				if(word.length() < 3) continue;
				String alphabetizedWord = alphabetize(word);
				List<String> list = map.get(alphabetizedWord);
				if (list == null)
					map.put(alphabetizedWord, list = new ArrayList<String>());
				list.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			scanner.close();
		}
		System.out.println(map.size());
		return map;
	}

	/*
	 * (non-Javadoc) 
	 * method result: 157.004 map entries after the removal of 1 and 2-letter words
	 * method result: 173.432 words total after the removal of 1 and 2-letter words
	 */
	/**
	 * Reads words from the .csv file and puts them into a multi-map.
	 * Only words composed of three or more letters are allowed to be saved in the database.
	 */
	public static Map<String, List<String>> readWords3() { 														   
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(PATH.toString()));
			while (scanner.hasNext()) {
				String word = scanner.next();
				if(word.length() < 3) continue;
				String alphabetizedWord = alphabetize(word);
				List<String> list = map.get(alphabetizedWord);
				if (list == null)
					map.put(alphabetizedWord, list = new ArrayList<String>());
				list.add(word);
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			scanner.close();
		}
		
		System.out.println("Parsing succesfull.\nNumber of key-value mappings: " + map.size());		
		return map;
	}
	
	/**
	 * Alphabetizes every single word.
	 * Alphabetizing means that all chars of a given word are being sorted lexicographically.
	 * New string created in that way is called an alphabetized word.
	 * @param s "real" word to be alphabetized
	 * @return an alphabetized word (a "non-real" word, a key in the multimap)
	 */
	private static String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}