package controller;
//GROWING PAINS - A Plant Shop system
//Mark Lambert - C00192497 - Object Oriented Software Development 2 - Year 2 Semester 2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;
import crud.crud;

public class growingpains {
		
		public static void main(String[] args) {
			//database URL using localmachine as host, with mysql sub protocol
			final String DATABSE_URL = "jdbc:mysql://localhost/GrowingPains";
			final String USER_NAME = "root";
			final String PASS_WORD = "Growing_pains123";
			
			Connection connection = null;
			Statement statement = null;
			//Declares instance of class crud
			crud c;
			
			//Instance of Address class
			Address adrs = new Address("Secondary St.", "Ballinaboola", "Wexford", "Y20X391","Ireland");
			//Instance of Customer
			Customer cust = new Customer("David", "DaveJ", "david@dave.com", "mmynameisdavid", 800, adrs);
			Plant p = new Plant("Alocasia", "Alocasia - Elepahnt's Ear. 13cm pot, suitable for semi-hydro", 14.99f, 25, "Alocasia - Tropical", "Experienced");
			Acessory a = new Acessory("12cm Teracotta Pot", "Natural teracotta pot beautiful for indoors and outdoors", 2.99f, 30, "Plant Pot" );


			try {
				connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
				//Create Statement for inserting into table
				statement = connection.createStatement();
				c = new crud(connection);
				//Insertion of customer
				c.insertCustomer(cust);
				//Insertion of plant item
				c.insertProduct(p);
				//Insertion of accessory items
				c.insertProduct(a);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					statement.close();
					connection.close();
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}


