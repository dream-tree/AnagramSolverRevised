/*package basicTests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.marcin.anagramSolver.Application.Alphabetized;
import com.marcin.anagramSolver.Application.HibernateUtility;

public class DatabaseGetTest {
	
	private static Session session;
	
	@BeforeClass
	public static void setUp() {		
		SessionFactory factory = HibernateUtility.getInstance();		
		session = factory.getCurrentSession();
		session.beginTransaction();
	}
	
	@Test
	public void shouldGetRightAnagrams() {
		Assert.assertEquals(session.get(Alphabetized.class, 1).getMappedAnagrams(), "aah aha ");
		Assert.assertEquals(session.get(Alphabetized.class, 999).getMappedAnagrams(), "appel apple pepla ");
		Assert.assertEquals(session.get(Alphabetized.class, 5555).getMappedAnagrams(), "enter rente terne treen ");
		Assert.assertEquals(session.get(Alphabetized.class, 11456).getMappedAnagrams(), "shawling whalings ");
	}
	
	@Test
	public void shouldNotEqualsToFakeAnagrams() {
		Assert.assertFalse((session.get(Alphabetized.class, 1).getMappedAnagrams()).equals("aah     aha "));
		Assert.assertNotEquals(session.get(Alphabetized.class, 999).getMappedAnagrams(), "appel apple pepla ppppp");
		Assert.assertNotEquals(session.get(Alphabetized.class, 5555).getMappedAnagrams(), " enter rente terne treen ");
		Assert.assertNotEquals(session.get(Alphabetized.class, 11456).getMappedAnagrams(), "shawling whalingss ");
	}
	
	@AfterClass
	public static void close() {		
		session.close();
	}
}*/