package model;
//GROWING PAINS - Mark Lambert - C00192497
//Customer class - Compositional aggregation w/ Account class
public class Customer {
//TODO Ask Jason about the use of setting up a static variable for each 
//object which will be placed into a DB, so that it corresponds with the auto increment
	
	private int customerID;
    private String fName;
    private String lName;
    private Account acc;
    
  //Constructor
    public Customer() {
    	
    }
    
    public Customer(String fName, String lName, String email, String password, int phone, Address address) {
    	setfName(fName);
    	setlName(lName);
    	//Composition
    	setAccount(email, password, phone, address);
    }

    //Constructor including customerID, used for Customer RETRIEVAL 
    public Customer(int customerID, String fName, String lName, String email, String password, int phone, Address address) {
    	setCustomerID(customerID);
    	setfName(fName);
    	setlName(lName);
    	setAccount(email, password, phone, address);
    }
    
    //Constructor including customerID, used for Customer RETRIEVAL 
    public Customer(int customerID, String fName, String lName, String email, String password, int phone, String address) {
    	setCustomerID(customerID);
    	setfName(fName);
    	setlName(lName);
    	setAccount(email, password, phone, address);
    }
    
    //Setters
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	//Account setter creates a new instance of Account object - Compositional Aggregation
	public void setAccount(String email, String password, int phone, Address address) {
		this.acc = new Account(email, password, phone, address);
	}
	
	//Account setter, which has a String for an address
	public void setAccount(String email, String password, int phone, String address) {
		this.acc = new Account(email, password, phone, address);
	}
	//Getters
	public String getfName() {
		return fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	//Calls Account getAddress() method to obtain the Address
	public String getAddress() {
		return acc.getAddress();
	}
	//Calls Account getEmail() method to obtain the email
	public String getEmail() {
		return acc.getEmail();
	}
	//Calls Account getPassword() method to obtain the password
	public String getPassword() {
		return acc.getPassword();
	}
	//Calls Account getPhone() method to obtain the phone number
	public int getPhone() {
		return acc.getPhone();
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fName=" + fName + ", lName=" + lName + ", acc=" + acc + "]";
	}

	

}