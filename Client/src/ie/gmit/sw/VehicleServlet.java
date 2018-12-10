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

@WebServlet("/Vehicles")
public class VehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public VehicleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/vehiclelist/get");

		// Get a response from the service
		// ClientResponse r = wr.accept("text/html").get(ClientResponse.class);
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Vehicle>>() {
		}.getType();

		List<Vehicle> vehicles = gson.fromJson(r, listType);
		// get the status of the response - if status = 200 we are connected and
		// response should return
		request.setAttribute("vehicles", vehicles);
		request.getRequestDispatcher("/WEB-INF/Vehicles.jsp").forward(request, response);

	}// get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("delButton") != null) {

			del(request, response);

		} else if (request.getParameter("updateButton") != null) {

			update(request, response);

		}

	}// post

	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/vehiclelist/delete");

		// Get a response from the service
		// ClientResponse r = wr.accept("text/html").get(ClientResponse.class);
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);
		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Vehicle>>() {
		}.getType();

		List<Vehicle> vehicles = gson.fromJson(r, listType);
		// get the status of the response - if status = 200 we are connected and
		// response should return
		request.setAttribute("vehicles", vehicles);
		request.getRequestDispatcher("/WEB-INF/Vehicles.jsp").forward(request, response);

	}// del

	private static void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Vehicles.jsp").forward(request, response);

	}// update
	
	
}
