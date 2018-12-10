package WebService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import ie.gmit.sw.Booking;
import ie.gmit.sw.Customer;
import ie.gmit.sw.DatabaseService;
import ie.gmit.sw.Vehicle;
@Path("bookinglist")
public class BookingResources {
	
	private String service = "/databaseService";
	private String address = "localhost:1099";


    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    	
    	//return the values needed
    	List<Object> rs = ds.ReadBookings("SELECT * FROM BOOKINGS");
    	
    	//Close the Connection
    	ds.Close();
    	
    	Gson gson = new Gson();
    	
        String jsonResp = gson.toJson(rs);
    	
        return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
        
    }
    
    @SuppressWarnings("unused")
    
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
public Response addBooking() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
		
    	String name = "";
    	String address = "";
    	
    	String reg = "";
    	String make = "";
    	String model = "";
    	
    	Booking b = new Booking(new Vehicle(reg, make, model, true), new Customer(name, address));
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    	
    	ds.Create("INSERT INTO BOOKINGS (REG, MAKE, MODEL, NAME, ADDRESS) VALUES ('"+reg+"', '"+make+"', '"+model+"', '"+name+"', '"+address+"');");
    	ds.Create("INSERT INTO VEHICLES (REG, MAKE, MODEL, BOOKED) VALUES ('"+reg+"', '"+make+"', '"+model+"', '1');");
    	ds.Create("INSERT INTO CUSTOMERS (NAME, ADDRESS) VALUES ('"+name+"', '"+address+"');");
    	
    	//return the values needed
    	List<Object> rs = ds.ReadBookings("SELECT * FROM BOOKINGS");
    	
    	//Close the Connection
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
    	
    	String reg = "";
    	String make = "";
    	String model = "";
    	
    	Booking b = new Booking(new Vehicle(reg, make, model, true), new Customer(name, address));
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    	
    	ds.Update("UPDATE TABLE BOOKINGS ( MAKE, MODEL, NAME, ADDRESS) VALUES ('"+make+"', '"+model+"', '"+name+"', '"+address+"') WHERE REG="+reg+"; ");
    	
    	//return the values needed
    	List<Object> rs = ds.ReadBookings("SELECT * FROM BOOKINGS");
    	
    	//Close the Connection
    	ds.Close();
    	
    	Gson gson = new Gson();
    	
        String jsonResp = gson.toJson(rs);
    	
        return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
    }
    
    @SuppressWarnings("unused")
	@DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	
    	String name = "";
    	String address = "";
    	
    	String reg = "";
    	String make = "";
    	String model = "";
    	
    	Booking b = new Booking(new Vehicle(reg, make, model, true), new Customer(name, address));
    	
    	//Connect using RMI to Database Server
    	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
    	
    	//Connect
    	ds.Connect();
    
    	ds.Delete("DELETE FROM BOOKINGS WHERE NAME ='"+name+"';");
    	ds.Update("UPDATE TABLE VEHICLES (BOOKED) VALUES ('0') WHERE REG="+reg+"; ");
    	
    	//return the values needed
    	List<Object> rs = ds.ReadBookings("SELECT * FROM BOOKINGS");
    	
    	//Close the Connection
    	ds.Close();
    	
    	Gson gson = new Gson();
    	
        String jsonResp = gson.toJson(rs);
    	
        return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
        
    }
}