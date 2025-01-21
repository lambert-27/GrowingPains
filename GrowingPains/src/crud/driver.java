package crud;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class driver {
		
		public static void main(String[] args) {
			//database URL
			final String DATABSE_URL = "jdbc:mysql://localhost/plantsystem";
				
			Connection connection = null;
			Statement statement = null;
			crud c;
			c = new crud(connection, statement);
			
			String f_name = "Mark";
			String l_name = "Lambert";
			String password = "yo123";
			String email = "NO";
			String address = "YES";
			int phone = 12393;
		
			try {
				connection = DriverManager.getConnection(DATABSE_URL);
				c.insertCustomer(f_name, l_name, email, address, password, phone);
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
