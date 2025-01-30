package model;
//PAINS - Mark Lambert - C00192497
//Plant subclass of Item

public class Plant extends Item {
	
//	TODO Modify SQL Product table to include species, difficulty and other additional info to be kept about an item, then for the QUERY of each class, depending on what
	//class, we call only for the relevant elements, eg getAllPlants would query only for species, difficulty, not accessoryType for example
	//Class instance variables
    private String species;
    private String difficulty;

    //Constructors
    //Default Constructor
    public Plant() {
    	
    }
    //TEMP RETRIEVAL 
    public Plant(int itemID, String itemName, String description, double price, int qty) {
    	//Calls super class constructor
    	super(itemName, description, price, qty);
    	super.setType(getType());
    }
    //Plant - INSERTION
    public Plant(String itemName, String description, double price, int qty, String species, String difficulty) {
    	//Calls super class constructor
    	super(itemName, description, price, qty);
    	super.setType(getType());
    	setSpecies(species);
    	setDifficulty(difficulty);
    }
    
    public Plant(int itemID, String itemName, String description, double price, int qty, String species, String difficulty) {
    	//Calls super class constructor
    	super(itemID, itemName, description, price, qty);
    	super.setType(getType());
    	setSpecies(species);
    	setDifficulty(difficulty);
    }

    //Setters
    public void setSpecies(String species) {
    	this.species = species;
    }
    
    public void setDifficulty(String difficulty) {
    	this.difficulty = difficulty;
    }
    
    //Getters
    public String getSpecies() {
    	return species;
    }
    
    public String getDifficulty() {
    	return difficulty;
    }
    
    //getType() method which invokes the getClass() from superclass Object
    public String getType() {
    	//Accessory is a subclass of Object, calling .getSimpleName() removes the package name
    	return this.getClass().getSimpleName();
    }
    


}