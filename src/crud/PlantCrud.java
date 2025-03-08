package crud;
//GROWING PAINS - Mark Lambert - C00192497
//plantCrud class - Purpose to hold all methods for relevant Retrieve operations for Plants

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Plant;

public class PlantCrud extends Crud{
	
	public PlantCrud() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}


//	Gets a Plant item in Product table
	public Plant getPlant(int productID) throws SQLException {
		//Instantiate a new empty Plant object
		Plant p = new Plant();
		
		ResultSet resultSet = null;
		
		try {
			//SELECT Query which has a WHERE condition that the type must be an Plant AND productID=?
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product where category='Plant' AND productID=?");
			pstat.setInt(1, productID);
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nPlant in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				p = new Plant(resultSet.getInt("productID"), 
						resultSet.getString("productName"), 
						resultSet.getString("description"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("qty"), 
						resultSet.getString("image_path"));
				System.out.println(p);
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving Product from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		return p;
	}
	
//	Deletes a Plant item from the Product table
	public void deletePlant(int productID) throws SQLException {
		
		try {
			//Prepared statement for Querying the Customer table
			PreparedStatement pstat = connection.prepareStatement("DELETE FROM Product WHERE productID=? AND category='Plant'");
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
		
//	Gets all Plant items in Product table
	public void getAllPlants() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product where category='Plant'");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("\n-----All Plants in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("productID") + ", " + resultSet.getString("productName") + ", " + 
				resultSet.getString("description") + ", " + resultSet.getDouble("price") + ", " +
				resultSet.getInt("qty") + ", " + resultSet.getString("category") + " " + resultSet.getString("image_path"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Plants from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}

	

	


}