package controller;
//GROWING PAINS - A Plant Shop system
//Mark Lambert - C00192497 - Object Oriented Software Development 2 - Year 2 Semester 2

import java.sql.SQLException;

import view.GrowingPains;
/**
 * Driver for GrowingPains application
 */
public class GrowingMains {
		
	/**
	 * Main
	 */
		public static void main(String[] args) {
			try {
				//New instance of GrowingPains application
				GrowingPains g = new GrowingPains();
				//Set GrowingPains to visible
				g.run();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
