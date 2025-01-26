package model;
//PAINS - Mark Lambert - C00192497
//Plant subclass of Item

public class Plant extends Item {

    private String species;
    private String difficulty;

    //Constructors
    public Plant(String itemName, String description, float price, int qty, String species, String difficulty) {
    	//Calls super class constructor
    	super(itemName, description, price, qty);
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
    
    //getType() method which invokes the Object.getClass() method - which gets the Plant class
    //Then returns Plant.getSiimpleName() to print out the String value of the class Plant
    public String getType() {
    	return this.getClass().getSimpleName();
    }
    


}