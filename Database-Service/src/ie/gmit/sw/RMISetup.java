package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RMISetup {

	public static void main(String[] args) throws Exception {
		
		//Create an instance of a DatabaseServiceImpl. As DatabaseServiceImpl implements the DatabaseService
		//interface, it can be referred to as a service type.
		DatabaseService ds = new DatabaseServiceImpl();
		
		ensureDataBaseHasValues();
		
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "databaseService"
		Naming.rebind("databaseService", ds);

		//Print a message to standard output
		System.out.println("Server ready.");

	}
	
	private static void ensureDataBaseHasValues() {
		
		try {
			
			Class.forName ("org.h2.Driver");
			Connection conn = DriverManager.getConnection ("jdbc:h2:~/BookingServiceDB", "","");
			
			DatabaseServiceImpl.createTables(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
