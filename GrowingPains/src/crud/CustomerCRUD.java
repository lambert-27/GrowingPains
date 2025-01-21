package crud;



//
//try {
//	
//	//Establish connection to database
//	connection = DriverManager.getConnection(DATABSE_URL, "root", "");
//	//Create Statement for inserting into table
//	statement = connection.createStatement();
//	//Insert Data into database
//	statement.executeUpdate("INSERT INTO customer(f_name, l_name, email, address)"
//	+ " VALUES" + "(" + f_name + ", " +  l_name + ", " + email + ", " + address + "')");
//	PreparedStatement pstat = connection.prepareStatement("INSERT INTO customer(f_name, l_name, email, address) VALUES(?,?,?,?)");
//	pstat.setString(1, f_name);
//	pstat.setString(2,  l_name);
//	//pstat.setString(3,  password);;
//	pstat.setString(3,  email);
//	pstat.setString(4, address);
//	pstat.executeUpdate();
//
//}
//catch(SQLException sqlException) {
//	sqlException.printStackTrace();
//}