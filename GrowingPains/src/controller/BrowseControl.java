package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.AccessoryCrud;
import crud.PlantCrud;
import crud.ProductCrud;
import model.DisplayItem;
import view.GrowingPains;

public class BrowseControl {
	private PlantCrud plantCrud;
	private AccessoryCrud accessoryCrud;
	private ProductCrud productCrud;
	
	public BrowseControl() throws SQLException {
		this.plantCrud = new PlantCrud();
		this.accessoryCrud = new AccessoryCrud();
		this.productCrud = new ProductCrud();
		
	}
	
	public void handleSelectedProduct() {
		GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Product");
	}
	
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
