public void fetch(int case) {
	
	Connection DTSconn = null;
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	}
	catch(ClassNotFoundException ex) {
	   System.out.println("Error: unable to load driver class!");
	   return;
	}
	try{
		DTSconn = DriverManager.getConnection("jdbc:sqlite:EJGallo.db");
	} catch (Exception e) {
		System.out.println(e);
		return;
	}
	
	Statement state = DTSconn.createStatement();
	
	if (case == 0) {	// We're on default screen. Showing most recent DTS; sorting by timestamp
		
		ResultSet DTSRelation = state.executeQuery("SELECT * FROM Incidents ORDER BY time_submitted);
		
		// ...
		return;
	}
	
	if (case == 1) {	// Sorting by priority = 1 (high)
		
		ResultSet DTSRelation = state.executeQuery("SELECT * FROM Incidents WHERE priority = 1");
		
		// ...
		return;
	}
	
	if (case == 2) {	// Sorting by priority = 2 (med)
		
		ResultSet DTSRelation = state.executeQuery("SELECT * FROM Incidents WHERE priority = 2");
		
		// ...
		return;
	}
	
	if (case == 3) {	// Sorting by priority = 3 (low)
		
		ResultSet DTSRelation = state.executeQuery("SELECT * FROM Incidents WHERE priority = 3");
		
		// ...
		return;
	}
	
	if (case == 4) {	// Sorting by overdue
		
		ResultSet DTSRelation = state.executeQuery("SELECT * FROM Incidents WHERE overdue = 1 ORDER BY priority);
		
		// ...
		return;
	}
	
	return;
}