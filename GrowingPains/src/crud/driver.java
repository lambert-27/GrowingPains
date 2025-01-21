package crud;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class driver {
		
		public static void main(String[] args) {
			//database URL using plesk + mariadb subprotocol
			final String DATABSE_URL = "jdbc:mariadb://c00192497.candept.com:3306/GrowingPains";
			final String USER_NAME = "user";
			final String PASS_WORD = "Growing_up27";
			
			Connection connection = null;
			Statement statement = null;
			crud c;
			c = new crud(connection, statement);

			String f_name = "John";
			String l_name = "nhoJ";
			String password = "yo123";
			String email = "NO";
			String address = "YES";
			int phone = 12393;
		
			try {
				connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
				//Create Statement for inserting into table
				statement = connection.createStatement();
				c.insertCustomer(f_name, l_name, email, address, password, phone);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					if(statement != null)
					statement.close();
					if(connection != null)
					connection.close();
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}
