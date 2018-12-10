package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl extends UnicastRemoteObject implements DatabaseService {
	private static final long serialVersionUID = 1L;
	
	// JDBC driver name and database URL 
	private static final String JDBC_DRIVER = "org.h2.Driver";   
   	private static final String DB_URL = "jdbc:h2:~/BookingServiceDB";

	private Connection conn = null;
	
	public DatabaseServiceImpl() throws RemoteException{
		super();
	}

	public void Connect() {
		
		try {
			
			Class.forName (JDBC_DRIVER);
		
			this.conn = DriverManager.getConnection (DB_URL, "","");
			
			System.out.println("Client Accessing Database");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("Failed to Connect");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void Create(String sql) {
		
		try {
			
			Statement stmt = this.conn.createStatement();
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
		}
		
	}
	
	public List<Object> ReadCustomers(String sql) {
		
		ResultSet rs = null;
		
    	//New List to return
    	ArrayList<Object> list = new ArrayList<>();
		
		try {
			
			Statement stmt = this.conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Customer customer = new Customer(rs.getString(1), rs.getString(2));
				
				list.add(customer);  
			} 
			
		} catch (SQLException e) {
			
		}
		
		return list;
	}
	
	public List<Object> ReadBookings(String sql) {
		
		ResultSet rs = null;
		
    	//New List to return
    	ArrayList<Object> list = new ArrayList<>();
		
		try {
			
			Statement stmt = this.conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Booking b = new Booking( new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), true), new Customer(rs.getString(4), rs.getString(5)));
				
				list.add(b);
			} 
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public List<Object> ReadVehicles(String sql) {
		
		ResultSet rs = null;
		
    	//New List to return
    	ArrayList<Object> list = new ArrayList<>();
		
		try {
			
			Statement stmt = this.conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Vehicle vehicle = new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
				
				list.add(vehicle);  
			} 
			
		} catch (SQLException e) {
			
		}
		
		return list;
	}
	
	public void Update(String sql) {
		
		try {
			
			Statement stmt = this.conn.createStatement();
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
		}
		
	}
	
	public void Delete(String sql) {
		
		try {
			Statement stmt = this.conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("SQL Error");
		}
		
	}
	
	public void Close() {
		
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createTables(Connection conn) {
		
		Statement stmt;
		
		try {
			
			stmt = conn.createStatement();
			
			DropTables(stmt);
			
			CreateVehiclesTable(stmt);
			CreateCustomersTable(stmt);
			CreateBookingsTable(stmt);
			
		} catch (SQLException e) {
			
			System.out.println("SQL Error");
		
		}
		
	}
	
	// Only need these while creating the tables, wont be used in the live program //
	// Also will not be included in the interface //
	private static void CreateBookingsTable(Statement stmt) {
		
		String sql =  "CREATE TABLE BOOKINGS "+
				 "(REG VARCHAR(255) not NULL,"+
				 "MAKE VARCHAR(255) not NULL, "+
				 "MODEL VARCHAR(255) not NULL,"+ 
				 "NAME VARCHAR(255) not NULL, "+ 
				 "ADDRESS VARCHAR(255) not NULL, "+
				 " PRIMARY KEY ( REG ))";
		
		try {
			
			stmt.execute(sql);
			sql =  "INSERT INTO BOOKINGS (REG, MAKE, MODEL, NAME, ADDRESS) VALUES ('04 D 64474', 'Audi', 'A4', 'Mike', 'Dublin');";
			stmt.execute(sql);
			sql =  "INSERT INTO BOOKINGS (REG, MAKE, MODEL, NAME, ADDRESS) VALUES ('131 G 278', 'Citreon', 'C4', 'John', 'Galway');";
			stmt.execute(sql);
			sql =  "INSERT INTO BOOKINGS (REG, MAKE, MODEL, NAME, ADDRESS) VALUES ('01 L 7869', 'Mazda', 'MX5', 'Ray', 'Galway');";
			stmt.execute(sql);
			sql =  "INSERT INTO BOOKINGS (REG, MAKE, MODEL, NAME, ADDRESS) VALUES ('181 D 1268', 'Mercedes', 'S-Class', 'Kevin', 'Mayo');";
			stmt.execute(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	private static void CreateVehiclesTable(Statement stmt) {
		
		String sql =  "CREATE TABLE VEHICLES"+
				 "(REG VARCHAR(255) not NULL, "+
				 "MAKE VARCHAR(255) not NULL, "+
				 "MODEL VARCHAR(255) not NULL,"+
				 "BOOKED BIT(1),"+
				 " PRIMARY KEY (REG))";
		
		try {
			
			stmt.execute(sql);
			sql =  "INSERT INTO VEHICLES (REG, MAKE, MODEL, BOOKED) VALUES ('04 D 64474', 'Audi', 'A4', '1');";
			stmt.execute(sql);
			sql =  "INSERT INTO VEHICLES (REG, MAKE, MODEL, BOOKED) VALUES ('131 G 278', 'Citreon', 'C4', '1');";
			stmt.execute(sql);
			sql =  "INSERT INTO VEHICLES (REG, MAKE, MODEL, BOOKED) VALUES ('01 L 7869', 'Mazda', 'MX5', '1');";
			stmt.execute(sql);
			sql =  "INSERT INTO VEHICLES (REG, MAKE, MODEL, BOOKED) VALUES ('181 D 1268', 'Mercedes', 'S-Class', '1');";
			stmt.execute(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	private static void CreateCustomersTable(Statement stmt) {
		
		String sql =  "CREATE TABLE CUSTOMERS"+
				 "(NAME VARCHAR(255) not NULL, "+
				 "ADDRESS VARCHAR(255) not NULL, "+
				 " PRIMARY KEY (NAME))";
		
		try {
			
			stmt.execute(sql);
			sql =  "INSERT INTO CUSTOMERS (NAME, ADDRESS) VALUES ('Mike', 'Dublin');";
			stmt.execute(sql);
			sql =  "INSERT INTO CUSTOMERS (NAME, ADDRESS) VALUES ('John', 'Galway');";
			stmt.execute(sql);
			sql =  "INSERT INTO CUSTOMERS (NAME, ADDRESS) VALUES ('Ray', 'Galway');";
			stmt.execute(sql);
			sql =  "INSERT INTO CUSTOMERS (NAME, ADDRESS) VALUES ('Kevin', 'Mayo');";
			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	private static void DropTables(Statement stmt) {
		
		String sql =  "DROP TABLE VEHICLES; DROP TABLE BOOKINGS; DROP TABLE CUSTOMERS;";
		
		try {
			
			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
