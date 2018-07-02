package com.marcin.AnagramSolver.DatabaseSetup;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class tests the MySQL database connection.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
public class JDBCTest {
		
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/anagramator2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		String username = "accessor";
		String password = "accessor";
				
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection successful!");
		} catch (Exception e) {
			System.out.println("Connection unsuccessful!");
			e.printStackTrace();
		}	
	}
}