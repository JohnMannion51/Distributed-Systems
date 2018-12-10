package ie.gmit.sw;

import java.rmi.*;
import java.util.List;

public interface DatabaseService extends Remote{
	
	public void Connect() throws RemoteException;
	
	public void Create(String sql) throws RemoteException;
	
	public List<Object> ReadCustomers(String sql) throws RemoteException;
	
	public List<Object> ReadVehicles(String sql) throws RemoteException;
	
	public List<Object> ReadBookings(String sql) throws RemoteException;
	
	public void Update(String sql) throws RemoteException;
	
	public void Delete(String sql) throws RemoteException;
	
	public void Close() throws RemoteException;

}
