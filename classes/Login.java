import java.sql.*;

public int login(string user, string pass) {
	
	Connection logConn = null;
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	}
	catch(ClassNotFoundException ex) {
	   System.out.println("Error: unable to load driver class!");
	   return 0;
	}
	try{
		logConn = DriverManager.getConnection("jdbc:sqlite:EJGallo.db");
	} catch (Exception e) {
		System.out.println(e);
		return 0;
	}
	
	// Get username separate to help distinguish error in input
	Statement state = logConn.createStatement();
	ResultSet tempUserRelation = state.executeQuery("SELECT username FROM Users where username = ?", [user]);
	if (tempUserRelation.next() == null) {
		System.out.println("The username you entered did not match an existing username. Please try again.");
		return 0;
	}
	
	// Get asssociated password and whether or not the user is a Super/Admin or a Distribution member
	ResultSet tempPassRelation = state.executeQuery("SELECT password, rank FROM Users where password = ? AND username = ?", [pass], [user]);
	if (tempPassRelation.next() == null) {
		System.out.println("The password you entered did not match the password connected to this username. Please try again.");
		return 0;
	}
	
	if (tempPassRelation.next() == 1) {
		return 2;	// Successful Super/Admin login
	} else {
		return 1;	// Successful Distribution-team login
	}
	
	// Shouldn't get here
	return 0;
}