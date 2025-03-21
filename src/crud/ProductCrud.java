package crud;
//GROWING PAINS - Mark Lambert - C00192497
//productCrud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks for Product

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DisplayItem;
import model.Item;

/**
 * The ProductCrud class provides methods for performing CRUD (Create, Retrieve, Update, Delete)
 * operations in the Product Table
*/
public class ProductCrud extends Crud{
	
	public ProductCrud() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Inserts a Product into the Product table 
	 * @param Item item An Item Object containing all details 
	 * (itemName, description, price, qty, type, imgPath)
	 * to be entered into the Product table.
	 * Note: imgPath is the associated file location to the image for each respective Product
	 */
	public void insertProduct(Item item) throws SQLException{
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Product(productName, description, price, qty, category, image_path) VALUES(?,?,?,?,?,?)");
			pstat.setString(1,  item.getItemName());
			pstat.setString(2,  item.getDescription());
			pstat.setDouble(3, item.getPrice());
			pstat.setInt(4,  item.getQty());
			pstat.setString(5, item.getType());
			pstat.setString(6, item.getImgPath());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			System.err.println("Error inserting Product into table : " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
	
	/**
	 *  Retrieves all Products from the Product table 
	 * @return A List of DisplayItem object's
	 */
	public List<DisplayItem> getAllProducts() throws SQLException {
		ResultSet resultSet = null;
//		ArrayList of products to store all selected products
		List<DisplayItem> products = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product");
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
	
	/**
	 *  Retrieves all Products from the Product table 
	 * @return A List of DisplayItem object's
	 */
	public List<DisplayItem> getProductsInStock() throws SQLException {
		ResultSet resultSet = null;
//		ArrayList of products to store all selected products
		List<DisplayItem> products = new ArrayList<>();
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category, image_path FROM Product WHERE qty>0");
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
	
	/**
	 *  Deletes an Product item from the Product table based on its product ID
	 * @param productID The ID of the product to retrieve
	 */
	public void deleteProduct(int productID) throws SQLException {
		
		try {
			//Prepared statement for Querying the Product table
			PreparedStatement pstat = connection.prepareStatement("DELETE FROM Product WHERE productID=?");
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
			System.err.println("Error retrieving Product from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
}
	/**
	 *  Updates a Product from the Product table based on its Product ID
	 * @param Item i The Item object with which is being updated
	 */
	public void updateItem(Item i) throws SQLException {
		try {
			PreparedStatement pstat = connection.prepareStatement("UPDATE Product SET productName=?, description=?, price=?, qty=?, category=?, image_path=? WHERE productID=?");
			pstat.setString(1,  i.getItemName());
			pstat.setString(2,  i.getDescription());
			pstat.setDouble(3, i.getPrice());
			pstat.setInt(4,  i.getQty());
			pstat.setString(5,  i.getType());
			pstat.setString(6,  i.getImgPath());
			pstat.setInt(7,  i.getItemID());
			pstat.executeUpdate();
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving Product from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
	/**
	 *  Updates a Product from the Product table based on its Product ID
	 * @param Item i The Item object with which is being updated
	 */
	public void updateQty(Item i, int amount) throws SQLException {
		try {			
			PreparedStatement pstat = connection.prepareStatement("UPDATE Product SET qty=? WHERE productID=?");
			pstat.setInt(1,  amount);
			pstat.setInt(2,  i.getItemID());
			pstat.executeUpdate();
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving Product from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
}
