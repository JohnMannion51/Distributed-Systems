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

import ie.gmit.sw.DatabaseService;

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
    
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBooking() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	System.out.println("testing add ");
    	
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
        
    }//addBooking
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.TEXT_PLAIN)
    public void updateBooking() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	System.out.println("testing update");
    }
    
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.TEXT_PLAIN)
    public void dBooking() throws RemoteException, MalformedURLException, NotBoundException, SQLException {
    	System.out.println("testing delete");
    }
    
}