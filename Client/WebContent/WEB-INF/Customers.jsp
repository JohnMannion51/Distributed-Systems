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

<title>Customer List</title>
</head>

<body>

	<h3>List of All Customers</h3>
	<table>
		<tr>
			<td style="height: 30px; width: 30px">NAME</td>
			<td style="height: 30px; width: 30px">ADDRESS</td>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td style="height: 30px; width: 50px">${customer.name}</td>
				<td style="height: 30px; width: 50px">${customer.address}</td>

				<td style="height: 30px; width: 30px">
					<form action="Customers" method="post">
						<input type="submit" name="updateButton" value="Update" />
					</form>
				</td>
				<td style="height: 30px; width: 30px">
					<form action="Customers" method="post">
						<input type="submit" name="delButton" value="Delete" />
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
	<form action="index.jsp">
		<input type="submit" value="Back" style="height: 30px; width: 200px" />
	</form>
</body>

</html>