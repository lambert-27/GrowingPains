package model;
//GROWING PAINS - Mark Lambert - C00192497
//Account Class - Aggregation relationship w/ Address class

/**
 * The Account class holds all information relative to each Customers account
 * This class has an aggregational relationship with the Address class, where 
 * the Address is a part of the Account, and may also exist independently of the Account
 * 
 * The Account class stores the email, address, password and phone number of a Customer
 */
public class Account {
//Class instance variables
    private String email;
    private Address address;
    private String password;
    private String phone;

    //Constructors
    /**
     * Constructor used for creating an Account Object for INSERTION
     * Creates the Account using the Address class
     * @param email The Email of the Account
     * @param password The Password of the Account
     * @param phone The Phone of the Account
     * @param address The Address of the Account
     */
    public Account(String email, String password, String phone, Address address) {
    	setEmail(email);
    	setPassword(password);
    	setPhone(phone);
    	//Aggregation
    	setAddress(address);
    }
    /**
     * Constructor used for creating an Account Object for RETRIEVAL
     * Creates the Account using the Address class
     * @param email The Email of the Account
     * @param password The Password of the Account
     * @param phone The Phone of the Account
     * @param address The Address of the Account
     */
    public Account(String email, String password, String phone, String address) {
    	setEmail(email);
    	setPassword(password);
    	setPhone(phone);
    	setAddress(address);
    }

    //Setters
    /**
     * Sets the address of the Account using an Address object, used for INSERTION
     * @param address The Address object associated with the account
     */
	public void setAddress(Address address) {
		this.address = address;
	}
    /**
     * Sets the address of the Account using an Address object, used for RETRIEVAL
     * @param address The String from which an Address object is then created
     */
	public void setAddress(String address){
		this.address = new Address(address);
	}
    /**
     * Sets the email of the Account 
     * @param email The Email associated with the Account
     */
	public void setEmail(String email) {
		this.email = email;
	}
    /**
     * Sets the password of the Account 
     * @param password The Password associated with the Account
     */
	public void setPassword(String password) {
		this.password = password;
	}
    /**
     * Sets the phone number of the Account 
     * @param phone The Phone Number associated with the Account
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	//Getters
    /**
     * Gets the email of the Account 
     * @return email The Email associated with the Account
     */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the full Address associated with the Account.
	 * Calls the toString() method of the Address class to return a formatted Address
	 * 
	 * @return A formatted String representing the Account's address
	 */
	public String getAddress() {
		return address.toString();
	}

    /**
     * Gets the password of the Account 
     * @return password The Password associated with the Account
     */
	public String getPassword() {
		return password;
	}
    /**
     * Gets the phone number of the Account 
     * @return The phone number associated with an account
     */
	public String getPhone() {
		return phone;
	}

}