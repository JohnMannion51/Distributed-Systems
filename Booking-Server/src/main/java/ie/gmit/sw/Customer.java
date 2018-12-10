package ie.gmit.sw;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String address;

	public Customer() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String n, String a) {
		//super();
		this.name = n;
		this.address = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
