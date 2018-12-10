<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Book A Car</title>
</head>
<h2>Enter Details To Make A Booking</h2>
<body>

	 <form action= "Bookings" method="post">
		<!-- <br/> Name <br/>
		<input type="text" name="Name" style="height:20px;width:150px"/>
		<br/> Address <br/>
		<input type="text" name="Address" style="height:20px;width:150px"/>
		<br/> Make <br/>
		<input type="text" name="Make" style="height:20px;width:150px"/>
		<br/> Model <br/>
		<input type="text" name="Model" style="height:20px;width:150px"/>-->
		<br/><br/>
		<input type="submit" value="Make Booking" style="height:25px;width:150px"/>
	</form>
	<br/>
	<form action= "Customers" method="get">
		<input type="submit" value="Show Customers" style="height:25px;width:150px"/>
	</form>
	<form action= "Vehicles" method="get">
		<input type="submit" value="Show Cars" style="height:25px;width:150px"/>
	</form>
	<form action= "Bookings" method="get">
		<input type="submit" value="Show Bookings" style="height:25px;width:150px"/>
	</form>
	
	
	
	
</body>
</html>