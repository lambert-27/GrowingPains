package crud;
//GROWING PAINS - Mark Lambert - C00192497
//accessoryCrud class - Purpose to hold all methods for relevant Retrieve tasks for Accessory Items

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.DisplayItem;
import model.Accessory;
import model.Plant;
import model.Item; 
import model.Order;

public class accessoryCrud {
//	Protected for package private, so that all child Crud classes can use the same connection
	protected Connection connection;
	
	//Constructor
	public accessoryCrud(Connection connection) {
		//When we instantiate the CRUD class, so to do we instantiate a Connection
		this.connection = connection;
	}
	
	

	
//	Gets all Accessory items in Product table
	public Accessory getAccessory(int productID) throws SQLException {
		//Instantiate a new empty Plant object
		Accessory a = new Accessory();
		
		ResultSet resultSet = null;

		try {
			//SELECT Query which has a WHERE condition that the type must be an Accessory AND productID=?
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product WHERE category='Accessory' AND productID=?");
			pstat.setInt(1, productID);
			
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nAccessory in Accessory Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				a = new Accessory(resultSet.getInt("productID"), 
						resultSet.getString("productName"), 
						resultSet.getString("description"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("qty"),
						resultSet.getString("image_path"));
				System.out.println(a);
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving Accessory from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		return a;
	}
	
//	Deletes an Accessory item from the Product table
	public void deleteAccessory(int productID) throws SQLException {
		
		try {
			//Prepared statement for Querying the Customer table
			PreparedStatement pstat = connection.prepareStatement("DELETE FROM Product WHERE productID=? AND category='Accessory'");
			pstat.setInt(1, productID);
			//rowsAffected is assigned the result of the query
			int rowsAffected = pstat.executeUpdate();

//			If a row was affected (greater than 0) then success!
			if(rowsAffected > 0){
				System.out.println("The Product with product ID: " + productID + " was deleted");
			}
//			Otherwise, not found
			else {
				System.out.println("The Product with product ID: " + productID + " was not found");
			}
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
}
	
//	Gets all Accessory items in Product table
	public void getAllAccessories() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product where category='Accessory'");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("\n-----All Accessories in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("productID") + ", " + resultSet.getString("productName") + ", " + 
				resultSet.getString("description") + ", " + resultSet.getDouble("price") + ", " +
				resultSet.getInt("qty") + ", " + resultSet.getString("category") + " " + resultSet.getString("image_path"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Accessories from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}

}
