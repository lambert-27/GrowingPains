package model;
//GROWING PAINS - Mark Lambert - C00192497
//Account Class - Aggregation relationship w/ Address class
public class Account {
//Class instance variables
    private String email;
    private Address address;
    private String password;
    private int phone;

    //Constructors
    //Account - INSERTION
    public Account(String email, String password, int phone, Address address) {
    	setEmail(email);
    	setPassword(password);
    	setPhone(phone);
    	//Aggregation
    	setAddress(address);
    }
    //Account - RETRIEVAL
    public Account(String email, String password, int phone, String address) {
    	setEmail(email);
    	setPassword(password);
    	setPhone(phone);
    	//Aggregation
    	setAddress(address);
    }

    //Setters
    //Sets address of account using class Address
    //INSERTION
	public void setAddress(Address address) {
		this.address = address;
	}
	//RETRIEVAL
	public void setAddress(String address){
		this.address = new Address(address);
	}
	//Sets account email
	public void setEmail(String email) {
		this.email = email;
	}
	//Sets account password
	public void setPassword(String password) {
		this.password = password;
	}
	//Sets account phone number
	public void setPhone(int phone) {
		this.phone = phone;
	}

	//Getters
	public String getEmail() {
		return email;
	}

	//Calls the Address class' toString() to get a full String of each Address element for formatting
	public String getAddress() {
		return address.toString();
	}

	public String getPassword() {
		return password;
	}

	public int getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", address=" + address + ", password=" + password + ", phone=" + phone + "]";
	}

	
    

}