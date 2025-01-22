package model;
//GROWING PAINS - Mark Lambert - C00192497
//Account Class - Aggregation relationship w/ Address class
public class Account {

    private String email;
    private Address address;
    private String password;
    private int phone;

    //Constructor
    public Account(String email, String password, int phone, Address address) {
    	setEmail(email);
    	setPassword(password);
    	setPhone(phone);
    	//Aggregation
    	setAddress(address);
    }

    //Setters
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	//Getters
	public String getEmail() {
		return email;
	}

	//Calls the Address class' toString() to get a full String of each Address element
	public String getAddress() {
		return address.toString();
	}

	public String getPassword() {
		return password;
	}

	public int getPhone() {
		return phone;
	}

	
    

}