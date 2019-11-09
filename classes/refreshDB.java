
public void refreshDB() {
	
	Connection conn = null;
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	}
	catch(ClassNotFoundException ex) {
	   System.out.println("Error: unable to load driver class!");
	   return;
	}
	try{
		conn = DriverManager.getConnection("jdbc:sqlite:EJGallo.db");
	} catch (Exception e) {
		System.out.println(e);
		return;
	}
	
	// setting overdue values depending on today's date
	Statement state = conn.createStatement();
	ResultSet tempUserRelation = state.executeQuery("UPDATE Incidents SET overdue = 1 WHERE ((julianday(incident_date) + 14) < (julianday(GETDATE()))) AND (priority = 2 OR priority = 3)");
	ResultSet tempUserRelation = state.executeQuery("UPDATE Incidents SET overdue = 1 WHERE ((julianday(incident_date) + 1) < (julianday(GETDATE()))) AND priority = 1");
	
	return;
}
