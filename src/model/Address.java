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
		return town + ", " + city + ", " + county + ", " + eircode + ", " + country;
	}
	
	
    


}