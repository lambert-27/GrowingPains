package controller;
//GROWING PAINS - A Plant Shop system
//Mark Lambert - C00192497 - Object Oriented Software Development 2 - Year 2 Semester 2

import java.sql.SQLException;

import crud.Crud;
import view.GrowingPains;
/**
 * Driver for GrowingPains application
 */
public class GrowingMains {
		
	/**
	 * Main
	 */
		public static void main(String[] args) {
			//Sample instances of all DB models
//			Address adrs = new Address("Primary Rd.", "Goldthorpe", "Wexford", "X09S631","Ireland");
//			Customer cust = new Customer("David", "DaveJ", "david@dave.com", "mmynameisdavid", 800, adrs);
//			Plant p = new Plant("String of Hearts", "String of Hearts - Vining indoor plant", 9.99f, 6, "String of Hearts", "Beginner", "images/soh.png");
//			Plant p1 = new Plant("String of Bananas", "String of Bananas - Succulent vining plant", 19.99f, 16, "String of Bananas", "Experienced", "images/sob.png");
//			Plant p2 = new Plant("Alocasia Poly", "Alocasia - Elephant's Ear or Poly", 15.99f, 14, "Alocasia", "Experienced", "images/alocasia.png");
//			Accessory a = new Accessory("8cm Pot with Wooden Stand", "Duck egg blue pot with wooden stand", 14.99f, 5, "Pot" , "images/pot_stand.png");
//			Order o = new Order(1, 11, adrs, 27.00f);
			try {
				GrowingPains g = new GrowingPains();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}

//JCombo boxes
//Hash passwords
//Error handling for various fields
//Error handling must go to a log file (pop-up to tell user the input data was invalid, or highlisgt a field in red text etc)
//Documentation up-to-date


