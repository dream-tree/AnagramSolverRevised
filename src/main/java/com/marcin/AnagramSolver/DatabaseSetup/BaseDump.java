package com.marcin.AnagramSolver.DatabaseSetup;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.marcin.AnagramSolver.Application.Alphabetized;
import com.marcin.AnagramSolver.Application.Anagram;

/**
 * Class saves all entries to the database.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public class BaseDump {
	
	private static Map<String, List<String>> map = WordParser.readWords3();

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Alphabetized.class)
				.addAnnotatedClass(Anagram.class)
				.buildSessionFactory();
		
		Session session;
		
		try {
			for(String s : map.keySet()) {
				session = factory.getCurrentSession();
				session.beginTransaction();
				Alphabetized alphabetized = new Alphabetized(0, s);					
				List<String> listOfanagrams = map.get(s);
				session.save(alphabetized);
				for(String t : listOfanagrams) {
					Anagram tempAnagram = new Anagram(0, t);
					alphabetized.add(tempAnagram);
					session.save(tempAnagram);
				}				
				session.getTransaction().commit();
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}