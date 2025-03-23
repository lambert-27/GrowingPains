package model;
//GROWING PAINS - Mark Lambert - C00192497
//Customer class - Compositional aggregation w/ Account class

/**
 * The Customer class represents a Customer using the plant shop system
 * This class has a compositional aggregation relationship with the Account object
 * meaning, the Account class CANNOT exist without the Customer object as 
 * each Customer must have an Account associated with it
 * 
 * The Customer class contains the customerID, fName, lName and Account associated with each Customer
 * Where the Account contains more precious information about the customer
 */
public class Customer {
	//Class instance variables
	private int customerID;
    private String fName;
    private String lName;
    private Account acc;
    private boolean isLoggedIn = false;
    
  //Default Constructor
    /**
     * Default Constructor for the Customer class
     * Initialises an empty Cusotmer object
     */
    public Customer() {
    	
    }
    
    /**
     * Constructs a Customer for INSERTION into the database.
     * Creates the Account object by aggregating the email, password, phone and address
     * 
     * @param fName The first name of the customer
     * @param lName The last name of the customer
     * @param email The email of the customer
     * @param password The password of the customer
     * @param phone The phone number of the customer
     * @param address The address of the customer
     */
    public Customer(String fName, String lName, String email, String password, String phone, Address address) {
    	setfName(fName);
    	setlName(lName);
    	//Composition
    	setAccount(email, password, phone, address);
    }


    /**
     * Constructs a Customer for UPDATING.
     * Creates the Account object by aggregating the email, password, phone and address
     * 
     * @param customerID The ID of the Customer
     * @param fName The first name of the customer
     * @param lName The last name of the customer
     * @param email The email of the customer
     * @param password The password of the customer
     * @param phone The phone number of the customer
     * @param address The address of the customer
     */
    public Customer(int customerID, String fName, String lName, String email, String password, String phone, Address address) {
    	setCustomerID(customerID);
    	setfName(fName);
    	setlName(lName);
    	setAccount(email, password, phone, address);
    }
    
    /**
     * Constructs a Customer for RETRIEVAL.
     * Creates the Account object by aggregating the email, password, phone and address
     * 
     * @param customerID The ID of the Customer
     * @param fName The first name of the customer
     * @param lName The last name of the customer
     * @param email The email of the customer
     * @param password The password of the customer
     * @param phone The phone number of the customer
     * @param address The address of the customer
     */
    public Customer(int customerID, String fName, String lName, String email, String password, String phone, String address) {
    	setCustomerID(customerID);
    	setfName(fName);
    	setlName(lName);
    	setAccount(email, password, phone, address);
    }
    
    //Setters
    /**
     * Sets the first name of the Customer
     * 
     * @param fName the Customer's first name
     */
	public void setfName(String fName) {
		this.fName = fName;
	}
	//Set customer last name
    /**
     * Sets the last name of the Customer
     * 
     * @param lName the Customer's last name
     */
	public void setlName(String lName) {
		this.lName = lName;
	}

    /**
     * Sets the custtomer's ID
     * 
     * @param customerID the Customer's ID
     */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	

	/**
	 * Sets the Account details for the Customer by using the Account Objects Constructor
	 * This method is used for INSERTION into the database
	 * 
	 * @param email The email of the Customer
	 * @param password The password of the Customer
	 * @param phone The phone number of the Customer
	 * @param address The address of the Customer
	 */
	public void setAccount(String email, String password, String phone, Address address) {
		this.acc = new Account(email, password, phone, address);
	}
	
	/**
	 * Sets the Account details for the Customer by using the Account Objects Constructor
	 * This method is used for RETRIEVAL from the database
	 * 
	 * @param email The email of the Customer
	 * @param password The password of the Customer
	 * @param phone The phone number of the Customer
	 * @param address The address of the Customer
	 */
	public void setAccount(String email, String password, String phone, String address) {
		this.acc = new Account(email, password, phone, address);
	}
	//Getters
    /**
     * Gets the first name of the Customer
     * 
     * @return The Customer's first name
     */
	public String getfName() {
		return fName;
	}
	//Set customer last name
    /**
     * Gets the last name of the Customer
     * 
     * @return lName the Customer's last name
     */
	public String getlName() {
		return lName;
	}
    /**
     * Gets the customer's ID
     * 
     * @return customerID the Customer's ID
     */
	public int getCustomerID() {
		return customerID;
	}
	
	/**
	 * Calls Account getAddress() method to obtain the Address
	 * @return the Address associated with the Account
	 */
	public String getAddress() {
		return acc.getAddress();
	}
	/**
	 * Calls Account getEmail() method to obtain the email
	 * 
	 * @return the Email associated with the Account
	 */
	public String getEmail() {
		return acc.getEmail();
	}
	/**
	 * Calls Account getPassword() method to obtain the password
	 * @return the Password associated with the Account
	 */
	public String getPassword() {
		return acc.getPassword();
	}
	/**
	 * Calls Account getPhone() method to obtain the phone number
	 * @return the phone number associated with the Account
	 */
	public String getPhone() {
		return acc.getPhone();
	}
    /**
     * Sets the customer's logged in status to true
     */
	public void setLoggedIn() {
		this.isLoggedIn = true;
	}
    /**
     * Gets the customer's logged in status
     * 
     * @return customers logged in status
     */
	public boolean getStatus() {
		return this.isLoggedIn;
	}

	public void setAddress(String address) {
		// TODO Auto-generated method stub
		acc.setAddress(address);
	}
}