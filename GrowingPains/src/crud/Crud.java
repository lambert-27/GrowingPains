package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Superclass for Crud

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The Crud is an abstract class serving as the connection point into the MySQL database.
 * It is intended to be extended by other classes that perform CRUD (Create, Retrieve, Update, Delete) operations
*/

public abstract class Crud {
//	Protected for package private, so that all child Crud classes can use the same connection
	protected static Connection connection;
	protected static Statement statement;
	//database URL using localmachine as host, with mysql sub protocol
	final String DATABSE_URL = "jdbc:mysql://localhost/GrowingPains";
////	Windows MYSQL login
//	final String USER_NAME = "plantman";
//	final String PASS_WORD = "Growing_up27";
//	Linux MySQL login
	final String USER_NAME = "root";
	final String PASS_WORD = "Growing_pains123";

	/**
	 * Constructs a new CRUD object and establishes a connection to the databases
	 * @throws SQLException should an error occur with connection
	 */
	public Crud() throws SQLException {
		connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
		//Create Statement for inserting into table
		statement = connection.createStatement();
	}
	/**
	 * Closes the connection and statement objects - freeing database resources from use
	 * @throws SQLException should an error occur with connection
	 */
	 public static void closeConnection() throws SQLException {
		 connection.close();
		 statement.close();
	 }
	
	
}
