package model;
//GROWING PAINS - Mark Lambert - C00192497
//Address Class 
public class Address {

	private String town;
	private String city;
	private String county;
	private String eircode;
	private String country;
	
	//Constructor
    public Address(String town, String city, String county, String eircode, String country) {
    	setTown(town);
    	setCity(city);
    	setCounty(county);
    	setEircode(eircode);
    	setCountry(country);
    }

    //A constructor for RETRIEVING Customers. The town attribute gets assigned the full concatenated Address
    public Address(String address) {
    	setTown(address);
    }
    
	//Setters
	public void setTown(String town) {
		this.town = town;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setEircode(String eircode) {
		this.eircode = eircode;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
    //Getters
	public String getTown() {
		return town;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public String getEircode() {
		return eircode;
	}

	public String getCountry() {
		return country;
	}

	//toString to style Address output to a visually pleasing format
	public String toString() {
		//If town has a value in it and city doesn't, that means we are RETRIEVING a customer, so we can return just town
		if(town != null && city == null) {
			return town;
		}
		//Otherwise, use this for insertion
		return town + ", " + city + ", " + county + ", " + eircode + ", " + country;
	}
	
	
    


}