package com.sears.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.sears.models.Person;

public class MainServices {
	
	
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
	
	 // Connect to the database
    String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    String username = "system";
    String password = "root";
    
	public MainServices() {
		super();
	}

	public List<Person> allProducts() {
		
		List<Person> persons = new ArrayList<>();
		
        // Establish a connection
        try {
        		
        	// Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
        		
        		
        	connection = DriverManager.getConnection(url, username, password);
        	
            System.out.println("Database connected!");

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute a SELECT query
            String query = "SELECT * FROM SYSTEM.PERSON";
            ResultSet resultSet = statement.executeQuery(query);
            
            
            // Process the result set
            while (resultSet.next()) {
            	
            	Person person = new Person();
            	
            	person.setFirstName(resultSet.getString("FIRSTNAME"));
            	person.setLastName(resultSet.getString("LASTNAME"));
            	person.setBirthDate(resultSet.getString("BIRTHDATE"));
            	person.setEmail(resultSet.getString("EMAIL"));
            	person.setPhoneNumber(resultSet.getString("PHONENUMBER"));
            	
            	persons.add(person) ;
            	

            }
        } catch (SQLException e) {
            System.err.println("Cannot connect to the database!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	System.err.println("Cannot find Oracle Driver!");
            e.printStackTrace();
        }
		return persons;
   }
	
	
}
