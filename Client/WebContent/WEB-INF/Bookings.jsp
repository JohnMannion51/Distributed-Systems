<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>

<title>Booking List</title>
</head>

<body>

	<h3>List of All Bookings</h3>
	<table>
		<tr>
			<td style="height: 30px; width: 30px">REG</td>
			<td style="height: 30px; width: 30px">MAKE</td>
			<td style="height: 30px; width: 30px">MODEL</td>
			<td style="height: 30px; width: 30px">CUSTOMER</td>
			<td style="height: 30px; width: 30px">CUSTOMER ADDRESS</td>
		</tr>
		<c:forEach items="${bookings}" var="booking">
			<tr>
				<td style="height: 30px; width: 200px">${booking.vehicle.reg}</td>
				<td style="height: 30px; width: 200px">${booking.vehicle.make}</td>
				<td style="height: 30px; width: 200px">${booking.vehicle.model}</td>
				<td style="height: 30px; width: 200px">${booking.vehicle.booked}</td>

				<td style="height: 30px; width: 30px">
					<form action="Bookings" method="post">
						<input type="submit" name="updateButton" value="Update" />
					</form>
				</td>
				<td style="height: 30px; width: 30px">
					<form action="Bookings" method="post">
						<input type="submit" name="delButton" value="Delete" />
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
	
	<form action= "Bookings" method="post">
		<br/>Car Reg<br/>
		<input type="text" name="reg" style="height:30px;width:200px"/>
		<br/>Your Name<br/>
		<input type="text" name="name" style="height:30px;width:200px"/>
		<br/>Address<br/>
		<input type="text" name="address" style="height:30px;width:200px"/>
		<br/>Make<br/>
		<input type="text" name="make" style="height:30px;width:200px"/>
		<br/>Model<br/>
		<input type="text" name="model" style="height:30px;width:200px"/>
		<br/><br/>
		<input type="submit" name="newBooking" value="Create Booking" style="height:30px;width:200px"/>
	</form>
	
	
	<form action="index.jsp">
		<input type="submit" value="Back" style="height: 30px; width: 200px" />
	</form>
</body>



</html>
