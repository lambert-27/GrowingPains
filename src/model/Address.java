package model;
//GROWING PAINS - Mark Lambert - C00192497
//Address Class 


/**
 * The Address class holds all information relative to each Customers Address
 * 
 * The Address class stores the town, city, county, eircode and country for each Customer
 */
public class Address {
	//Class instance variables
	private String town;
	private String city;
	private String county;
	private String eircode;
	private String country;
	
	//Constructor
    /**
     * Constructor used for creating an Address Object for INSERTION
     * @param town The Town of the Customer
     * @param city The City of the Customer
     * @param county The County of the Customer
     * @param eircode The Eircode of the Customer
     * @param country The Country of the Customer
     * 
     */
    public Address(String town, String city, String county, String eircode, String country) {
    	setTown(town);
    	setCity(city);
    	setCounty(county);
    	setEircode(eircode);
    	setCountry(country);
    }

    /**
     * Constructor used for creating an Address Object for RETRIEVAL of CUSTOMERS
     * The town attribute gets assigned the full concatenated Address retrieved from DB
     * @param address The address of the Customer
     * 
     */
    public Address(String address) {
    	setTown(address);
    }
    
	//Setters
    /**
     * Sets the town of the Customer
     * @param town The Town in the Address associated with the Customer's Account
     * 
     */
	public void setTown(String town) {
		this.town = town;
	}
    /**
     * Sets the city of the Customer
     * @param city The City in the Address associated with the Customer's Account
     * 
     */
	public void setCity(String city) {
		this.city = city;
	}
    /**
     * Sets the county of the Customer
     * @param county The County in the Address associated with the Customer's Account
     * 
     */
	public void setCounty(String county) {
		this.county = county;
	}
    /**
     * Sets the eircode of the Customer
     * @param eircode The Eircode in the Address associated with the Customer's Account
     * 
     */
	public void setEircode(String eircode) {
		this.eircode = eircode;
	}
    /**
     * Sets the country of the Customer
     * @param country The Country in the Address associated with the Customer's Account
     * 
     */
	public void setCountry(String country) {
		this.country = country;
	}
	
    //Getters
    /**
     * Gets the town of the Customer
     * @return town The Town in the Address associated with the Customer's Account
     * 
     */
	public String getTown() {
		return town;
	}

    /**
     * Gets the city of the Customer
     * @return city The City in the Address associated with the Customer's Account
     * 
     */
	public String getCity() {
		return city;
	}

    /**
     * Gets the county of the Customer
     * @return county The County in the Address associated with the Customer's Account
     * 
     */
	public String getCounty() {
		return county;
	}

    /**
     * Gets the eircode of the Customer
     * @return eircode The Eircode in the Address associated with the Customer's Account
     * 
     */
	public String getEircode() {
		return eircode;
	}

    /**
     * Gets the county of the Customer
     * @return county The County in the Address associated with the Customer's Account
     * 
     */
	public String getCountry() {
		return country;
	}

    /**
     * Override toString method which checks if the method is called for RETRIEVAL or INSERTION
     * @return The String representing the customers Address
     * 
     */
	public String toString() {
		//If town has a value in it and city doesn't, that means we are RETRIEVING a customer, so we can return just town
		if(town != null && city == null) {
			return town;
		}
		//Otherwise, use this for insertion
		return town + ", " + city + ", " + county + ", " + eircode + ", " + country;
	}
	
}