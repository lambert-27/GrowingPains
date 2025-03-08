package model;
//GROWING PAINS - Mark Lambert - C00192497
//Catalogue Class - Aggreagates w/ Item in order to create a dynamic catalogue of items 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.ProductCrud;

public class Catalogue {
//	Catalogue aggregates w/ Item (For the purposes of displaying the item, another class DisplayItem has been created)
	private List<DisplayItem> items;
	
	
    public Catalogue() {
//    	Instantiate the list to a new ArrayList
    	this.items = new ArrayList<>();
    }
    
//    getCatalogue, instansiates a local scope ProductsCrud variable in order to populate the ArrayList with all the products in the db
    public void getCatalogue() throws SQLException {
    	ProductCrud crud = new ProductCrud();
    	items = crud.getAllProducts();
    }
    
//    displayCatalogue calls getCatalogue (encapsulating it within the displayCatalogue method) and promptly returns the list
    public List<DisplayItem> displayCatalogue() throws SQLException{
    	getCatalogue();
    	return items;
    }

}