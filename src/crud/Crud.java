package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Superclass for Crud

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Crud {
//	Protected for package private, so that all child Crud classes can use the same connection
	protected Connection connection;
	protected Statement statement;
	//database URL using localmachine as host, with mysql sub protocol
	final String DATABSE_URL = "jdbc:mysql://localhost/GrowingPains";
////	Windows MYSQL login
	final String USER_NAME = "plantman";
	final String PASS_WORD = "Growing_up27";
//	Linux MySQL login
//	final String USER_NAME = "root";
//	final String PASS_WORD = "Growing_pains123";

	//Constructor
	public Crud() throws SQLException {
		connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
		//Create Statement for inserting into table
		statement = connection.createStatement();
	}
	
}
