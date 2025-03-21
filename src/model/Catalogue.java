package model;
//GROWING PAINS - Mark Lambert - C00192497
//Catalogue Class - Aggreagates w/ Item in order to create a dynamic catalogue of items 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.ProductCrud;


/**
 * The Catalogue class manages a collection of items in a dynamic catalogue of Products
 * This class aggregates with the DisplayItem class, which is used to display each individual item
 * in the catalogue. The Catalogue is populated by fetching Product data from the database
 * by using the ProductCrud class
 * 
 * The Catalogue class contains a List of DisplayItem Objects, representing the products in the Catalogue
 */
public class Catalogue {
//	Catalogue aggregates w/ Item (For the purposes of displaying the item, another class DisplayItem has been created)
	private List<DisplayItem> items;
	
	/**
	 * Constructs a new Catalogue Object and initialises the list of items
	 */
    public Catalogue() {
//    	Instantiate the list to a new ArrayList
    	this.items = new ArrayList<>();
    }
    
    /**
     * Retrieves all products from the database and populates the List of DisplayItem's by using 
     * a local scope ProductCrud class variable which queries the database for all Products
     */
    public void getCatalogue() throws SQLException {
    	ProductCrud crud = new ProductCrud();
    	items = crud.getProductsInStock();
    }

    /**
     * Displays the catalogue by calling getCatalogue this.getCatalogue to fetch all products
     * and then returns the list of items
     * 
     * @return A list of all Products in the Catalogue
     */
    public List<DisplayItem> displayCatalogue() throws SQLException{
    	getCatalogue();
    	return items;
    }

}