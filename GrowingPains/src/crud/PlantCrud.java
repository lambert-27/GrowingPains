package crud;
//GROWING PAINS - Mark Lambert - C00192497
//plantCrud class - Purpose to hold all methods for relevant Retrieve operations for Plants

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DisplayItem;
import model.Plant;
/**
 * The PlantCrud class provides methods for performing CRUD (Create, Retrieve, Update, Delete)
 * operations in the Product Table
*Specifically, this class provides methods for Plants of type Plant
*/
public class PlantCrud extends Crud{
	/**
	 * Constructs an PlantCrud object used for CRUD operations on Plant items
	 * @throws SQLException Error should a connection problem occur
	 */
	public PlantCrud() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 *  Retrieves an Plant item from the Product table based on its product ID
	 * @param productID The ID of the product to retrieve
	 * @return An Plant object
	 * @throws SQLException Error should a plant not be found in the table
	 */
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
	
	/**
	 * Deletes an Plant item from the Product table based on its product ID
	 * @param productID The ID of the product to retrieve
	 * @throws SQLException Error should a plant not be found in the table
	 */
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
		
	/**
	 *  Retrieves all Plants from the Product table 
	 * @return A List of DisplayItem object's
	 * @throws SQLException Error should a product not be found in the table
	 */
	public List<DisplayItem> getAllPlants() throws SQLException {
		ResultSet resultSet = null;
//		ArrayList of products to store all selected products
		List<DisplayItem> products = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product WHERE category='Plant'");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
//			Check if resultSet has a value
			while(resultSet.next()) {
				int id = resultSet.getInt("productID");
				String name = resultSet.getString("productName");
				String desc = resultSet.getString("description");
				double price = resultSet.getDouble("price");
				int qty = resultSet.getInt("qty");
				String image_path = resultSet.getString("image_path");
				
				DisplayItem product = new DisplayItem(id, name, desc, price, qty, image_path);
				products.add(product);
				}
			}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Products from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		
		return products;
	}

	

	


}