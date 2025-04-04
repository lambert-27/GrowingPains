package crud;
//GROWING PAINS - Mark Lambert - C00192497
//accessoryCrud class - Purpose to hold all methods for relevant Retrieve tasks for Accessory Items

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Accessory;
import model.DisplayItem;

/**
 * The AccessoryCrud class provides methods for performing CRUD (Create, Retrieve, Update, Delete)
 *operations in the Product Table
*Specifically, this class provides methods for Products of type Accessory
*/

public class AccessoryCrud extends Crud {

	/**
	 * Constructs an AccessoryCrud object which initialises the database connection via its superclass
	 * @throws SQLException Error should a connection problem occur
	 */
	public AccessoryCrud() throws SQLException {
		super();
	}
		
/**
 *  Retrieves an Accessory item from the Product table based on its product ID
 * @param productID The ID of the product to retrieve
 * @return An Accessory object
 * @throws SQLException Error should an accessory not be found in the table
 */
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
	
/**
 * Deletes an Accessory item from the Product table based on its product ID
 * @param productID The ID of the product to retrieve
 *  @throws SQLException Error should an accessory not be found in the table
 */
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
			System.err.println("Error retrieving Accesoory from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
}
	
	/**
	 *  Retrieves all Accessories from the Product table 
	 * @return A List of DisplayItem object's
	 * @throws SQLException Error should a product not be found in the table
	 */
	public List<DisplayItem> getAllAccessories() throws SQLException {
		ResultSet resultSet = null;
//		ArrayList of products to store all selected products
		List<DisplayItem> products = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product WHERE category='Accessory'");
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
