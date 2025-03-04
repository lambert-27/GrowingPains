package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Superclass for Crud

import java.sql.Connection;

public abstract class Crud {
//	Protected for package private, so that all child Crud classes can use the same connection
	protected Connection connection;
	
	//Constructor
	public Crud(Connection connection) {
		//When we instantiate the CRUD class, so to do we instantiate a Connection
		this.connection = connection;
	}
	
	
	


}
