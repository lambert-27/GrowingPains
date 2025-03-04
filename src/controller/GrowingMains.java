package controller;
//GROWING PAINS - A Plant Shop system
//Mark Lambert - C00192497 - Object Oriented Software Development 2 - Year 2 Semester 2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.*;
import crud.*;
import view.GrowingPains;

public class GrowingMains {
		
		public static void main(String[] args) {
			//database URL using localmachine as host, with mysql sub protocol
			final String DATABSE_URL = "jdbc:mysql://localhost/GrowingPains";
////			Windows MYSQL login
			final String USER_NAME = "plantman";
			final String PASS_WORD = "Growing_up27";
//			Linux MySQL login
//			final String USER_NAME = "root";
//			final String PASS_WORD = "Growing_pains123";

			Connection connection = null;
			Statement statement = null;



			//Instance of Address class
			Address adrs = new Address("Primary Rd.", "Goldthorpe", "Wexford", "X09S631","Ireland");
			//Instance of Customer
			Customer cust = new Customer("David", "DaveJ", "david@dave.com", "mmynameisdavid", 800, adrs);
			//Note; The following are child class' of the superclass Item
			//Instance of plant class
			Plant p = new Plant("Maidenhair Fern", "Maidenhair Fern w/ 6cm pot, suitable for all owners", 6.99f, 12, "Fern", "Advanced", "images/fern.png");
			//Instance of Accessory class
			Accessory a = new Accessory("Green Pot", "Green Pot w/ Eye Design", 8.99f, 3012, "Pot" , "images/pot_eyes.png");
			//Instance of Order class
			Order o = new Order(1, 11, adrs, 27.00f);
			try {
				connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
				//Create Statement for inserting into table
				statement = connection.createStatement();
				//Declares instance of class crud
				accessoryCrud ac = new accessoryCrud(connection);
				plantCrud pl = new plantCrud(connection);
				orderCrud or = new orderCrud(connection);
				productCrud pr = new productCrud(connection);
				customerCrud cu = new customerCrud(connection);
//				Insertion of customer
//				cu.insertCustomer(cust);
				//Insertion of plant item
				pr.insertProduct(p);
				//Insertion of accessory items
				pr.insertProduct(a);
//				or.insertOrder(o);
				
				//RETRIEVE tests
//				 cu.getCustomer(1);
//				 pl.getPlant(1);
//				 ac.getAccessory(1);
//				 cu.getAllCustomers();
//				 ac.getAllAccessories();
//				 pl.getAllPlants();
//				 pr.getAllProducts();
//				or.getOrder(1);
				GrowingPains g = new GrowingPains(pr.getAllProducts());
//				DELETE TESTS
//				cu.deleteCustomer(1);
//				ac.deleteAccessory(2);
//				pl.deletePlant(1);
//				pr.deleteProduct(14);
//				cu.updateCustomer(cust, 1);
//				pr.updateItem(p, 5);
//				or.updateOrder(o, 1);

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

//JCombo boxes
//Hash passwords, make it so we can login
//Show enry in GUI and then show the table also updating
//Error handling for various fields
//Error handling must go to a log file (pop-up to tell user the input data was invalid, or highlisgt a field in red text etc)
//Documentation up-to-date


