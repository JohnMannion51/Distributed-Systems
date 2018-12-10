package WebService;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("myresource")
public class MyResource {
	
	//private String service = "/databaseService";
	//private String address = "localhost:1099";


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        
        return "Got it!";
        
    }
    
    @Path("/Customers")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getCustomers() {
        
    	
        return "Got it!";
    }
}

/*
try
{
	DatabaseService ds = (DatabaseService)Naming.lookup( "rmi://" + address + service);
	
	/*ds.Connect();
	System.out.println("Connection made");
	ds.Close();
	System.out.println("Connection Closed");
	
} 
catch (Exception e) 
{
   e.printStackTrace();
}
*/
