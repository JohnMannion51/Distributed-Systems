package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.sun.jersey.api.client.WebResource;

@WebServlet("/Bookings")
public class BookingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BookingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/bookinglist/get");

		// Get a response from the service
		// ClientResponse r = wr.accept("text/html").get(ClientResponse.class);
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Booking>>() {
		}.getType();

		List<Booking> bookings = gson.fromJson(r, listType);
		// get the status of the response - if status = 200 we are connected and
		// response should return
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/WEB-INF/Bookings.jsp").forward(request, response);

	}// get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("delButton") != null) {

			System.out.println("DELETE");
			del(request, response);

		} else if (request.getParameter("updateButton") != null) {

			System.out.println("UPDATE");
			update(request, response);

		} else if (request.getParameter("newBooking") != null) {

			System.out.println("POST");
			add(request, response);

		}

	}// post

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Client client = Client.create();
		
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/add").post();
	}//add
	
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client client = Client.create();
		
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/update").put();

	}//update

	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/delete").delete();

	}//delete

	
	

}//BookingServlet