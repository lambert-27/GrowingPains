package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.Customer;
import model.Address;
public class driver {
		
		public static void main(String[] args) {
			//database URL using plesk + mariadb subprotocol
			final String DATABSE_URL = "jdbc:mysql://localhost:3306/growingpains";
			final String USER_NAME = "user";
			final String PASS_WORD = "Growing_up27";
			
			Connection connection = null;
			Statement statement = null;
			//Declares instance of class crud
			crud c;

			//Instance of Address class
			Address adrs = new Address("Main St.", "Carlow", "Carlow", "R93823","Ireland");
			//Instance of Customer
			Customer cust = new Customer("John", "nhoJ", "123@123.com", "strongpassword", 1111, adrs);


			try {
				connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
				//Create Statement for inserting into table
				statement = connection.createStatement();
				c = new crud(connection);
				c.insertCustomer(cust);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					statement.close();
					connection.close();
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}


