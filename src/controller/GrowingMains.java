package controller;
//GROWING PAINS - A Plant Shop system
//Mark Lambert - C00192497 - Object Oriented Software Development 2 - Year 2 Semester 2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;
import crud.Crud;

public class GrowingMains {
		
		public static void main(String[] args) {
			//database URL using localmachine as host, with mysql sub protocol
			final String DATABSE_URL = "jdbc:mysql://localhost/GrowingPains";
//			Windows MYSQL login
			final String USER_NAME = "plantman";
			final String PASS_WORD = "Growing_up27";
////			Linux MySQL login
//			final String USER_NAME = "root";
//			final String PASS_WORD = "Growing_pains123";
			
			Connection connection = null;
			Statement statement = null;
			//Declares instance of class crud
			Crud c;
			
			//Instance of Address class
			Address adrs = new Address("Secondary St.", "Ballinaboola", "Wexford", "Y20X391","Ireland");
			//Instance of Customer
			Customer cust = new Customer("David", "DaveJ", "david@dave.com", "mmynameisdavid", 800, adrs);
			
			//Note; The following are child class' of the superclass Item
			//Instance of plant class
			Plant p = new Plant("Alocasia", "Alocasia - Elepahnt's Ear. 13cm pot, suitable for semi-hydro", 14.99f, 25, "Alocasia - Tropical", "Experienced");
			//Instance of Accessory class
			Accessory a = new Accessory("12cm Teracotta Pot", "Natural teracotta pot beautiful for indoors and outdoors", 2.99f, 30, "Plant Pot" );


			try {
				connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
				//Create Statement for inserting into table
				statement = connection.createStatement();
				c = new Crud(connection);
//				Insertion of customer
//				c.insertCustomer(cust);
//				//Insertion of plant item
//				c.insertProduct(p);
//				//Insertion of accessory items
//				c.insertProduct(a);
				
				//RETRIEVE tests
				// c.getCustomer();
				// c.getPlant();
				// c.getAccessory();
				// c.getAllCustomers();
				// c.getAllAccessories();
				// c.getAllPlants();
				// c.getAllProducts();
				c.deleteCustomer(1);
				c.deleteAccessory(2);
				c.deletePlant(1);
				c.deleteProduct(14);
			} catch (SQLException e) {
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


