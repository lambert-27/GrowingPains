package controller;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains controller package - BrowseControl - Controls the logic behind the different GUI operations when in the Browse section of the application
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.AccessoryCrud;
import crud.PlantCrud;
import crud.ProductCrud;
import model.DisplayItem;
import view.GrowingPains;

/**
 * BrowseControl handles all of the logic behind the different GUI operations when in the Browse section of the system
 */
public class BrowseControl {
	private PlantCrud plantCrud;
	private AccessoryCrud accessoryCrud;
	private ProductCrud productCrud;
		
	/**
	 * Constructs a new BrowseControl object, instantiating the relevant CRUD objects needed
	 * @throws SQLException Exception thrown if a connection error occurs 
	 */
	public BrowseControl() throws SQLException {
		this.plantCrud = new PlantCrud();
		this.accessoryCrud = new AccessoryCrud();
		this.productCrud = new ProductCrud();
		
	}
	
	/**
	 * Switches the layout of the main GrowingPains app when they select a particular product by utilising the main CardLayout
	 */
	public void handleSelectedProduct() {
		GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Product");
	}
	
	/**
	 * Method which handles the logic for filtering a catalogue of items
	 * @param selectedFilter The filter the user selected from the JComboBox of filters
	 * @return the filtered Catalogue object
	 * @throws SQLException Exception thrown when an error occurs with the query on the Product table
	 */
	public List<DisplayItem> filterCatalogue(String selectedFilter) throws SQLException {
		List<DisplayItem> filteredCatalogue = new ArrayList<DisplayItem>();
		if(selectedFilter.equals("Plant")) {
			filteredCatalogue = plantCrud.getAllPlants();
		}
		else if(selectedFilter.equals("Accessory")) {
			filteredCatalogue = accessoryCrud.getAllAccessories();
		}
		else
			filteredCatalogue = productCrud.getAllProducts();
		
		return filteredCatalogue;
			
		}
}
