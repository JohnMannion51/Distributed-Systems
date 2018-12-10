package WebService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import com.google.gson.Gson;

import ie.gmit.sw.Customer;
import ie.gmit.sw.DatabaseService;

@Path("customerlist")
public class CustomerResources {

	private String service = "/databaseService";
	private String address = "localhost:1099";

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJson() throws RemoteException, MalformedURLException, NotBoundException, SQLException {

		// Connect using RMI to Database Server
		DatabaseService ds = (DatabaseService) Naming.lookup("rmi://" + address + service);

		// Connect
		ds.Connect();

		// return the values needed
		List<Object> rs = ds.ReadCustomers("SELECT * FROM CUSTOMERS");

		// Close the Connection
		ds.Close();

		Gson gson = new Gson();

		String jsonResp = gson.toJson(rs);

		return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();

	}
	@SuppressWarnings("unused")
	@PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	
    	
    	String name = "";
    	String address = "";
    	
    	
    	Customer customer = new Customer(name, address);
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    	
    	ds.Update("UPDATE TABLE CUSTOMERS (NAME, ADDRESS) VALUES ('"+name+"', '"+address+"') WHERE NAME="+name+"; ");
    	
    	//return the values needed
    	List<Object> rs = ds.ReadCustomers("SELECT * FROM CUSTOMERS");
    	
    	//Close the Connection
    	ds.Close();
    	
    	Gson gson = new Gson();
    	
        String jsonResp = gson.toJson(rs);
    	
        return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
    }
    
    @SuppressWarnings("unused")
	@DELETE
    @Path("/delete")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	
    	String name = "";
    	String address = "";
    	
    	String reg = "";
    	String make = "";
    	String model = "";
    	
    	Customer customer = new Customer(name, address);
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    
    	ds.Delete("DELETE FROM CUSTOMERS WHERE NAME ='"+name+"';");
    	ds.Delete("DELETE FROM BOOKINGS WHERE NAME ='"+name+"';");
    	ds.Update("UPDATE TABLE VEHICLES (BOOKED) VALUES ('0') WHERE NAME="+name+"; ");
    	
    	//return the values needed
    	List<Object> rs = ds.ReadBookings("SELECT * FROM CUSTOMERS");
    	
    	//Close the Connection
    	ds.Close();
    	
    	Gson gson = new Gson();
    	
        String jsonResp = gson.toJson(rs);
    	
        return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
        
    }
	
}
