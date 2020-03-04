package ie.gmit.sw;

import java.io.Serializable;

public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	private Vehicle vehicle;
	private Customer customer;

	public Booking() {

	}

	public Booking(Vehicle vehicle, Customer customer) {
		super();
		this.vehicle = vehicle;
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}//Booking Class
