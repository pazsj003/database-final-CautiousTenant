<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Cheapest house by city</title>
</head>
<body>
  <img src="https://cdn.houseplans.com/product/o2d2ui14afb1sov3cnslpummre/w1024.jpg?v=15" 
       alt="Smiley face" height=30% width=30%  >
	<form action="findcheapesthouses" method="post">
		<h1>Cheapest House By City</h1>
		<p>
			<label for="city">City</label> <input id="city" name="city"
				value="${fn:escapeXml(param.city)}">
		</p>
		<p>
			<input type="submit"> <br />
			<br />
			<br /> <span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br />
	<!-- <div id="townHouseCreate"><a href="townhousecreate">Create TownHouse</a></div> -->
	<br />
	<h1>Cheapest House</h1>
	<table border="1">
		<tr>

			<th>HouseId</th>
			<th>Street1</th>
			<th>Street2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Availability</th>
			<th>AvailableDate</th>
			<th>OwnerId</th>
			<th>Description</th>
			<th>Flooring</th>
			<th>Bedrooms</th>
			<th>Bathrooms</th>
			<th>Deposit</th>
			<th>Rating</th>
			<th>Pets</th>
			<th>Parking</th>
			<th>Laundry</th>
			<th>YearOfBuild</th>
			<th>HouseRent</th>


			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${cheapesthouses}" var="house">
			<tr>
				<td><c:out value="${house.getHouseId()}" /></td>
				<td><c:out value="${house.getStreet1()}" /></td>
				<td><c:out value="${house.getStreet2()}" /></td>
				<td><c:out value="${house.getCity()}" /></td>
				<td><c:out value="${house.getState()}" /></td>
				<td><c:out value="${house.getZip()}" /></td>
				<td><c:out value="${house.getAvailability()}" /></td>
				<td><c:out value="${house.getAvailableDate()}" /></td>
				<td><c:out value="${house.getOwner().getOwnerId()}" /></td>
				<td><c:out value="${house.getDescription()}" /></td>
				<td><c:out value="${house.getFlooring()}" /></td>
				<td><c:out value="${house.getBedrooms()}" /></td>
				<td><c:out value="${house.getBathrooms()}" /></td>
				<td><c:out value="${house.getDeposit()}" /></td>
				<td><c:out value="${house.getRating()}" /></td>
				<td><c:out value="${house.getPets()}" /></td>
				<td><c:out value="${house.getParking()}" /></td>
				<td><c:out value="${house.getLaundry()}" /></td>
				<td><c:out value="${house.getYearOfBuilder()}" /></td>
				<td><c:out value="${house.getRent()}" /></td>



			</tr>
		</c:forEach>
	</table>
</body>
</html>
