<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a TownHouse</title>
</head>
<body>
<img src="https://cdn.houseplans.com/product/o2d2ui14afb1sov3cnslpummre/w1024.jpg?v=15" 
       alt="Smiley face" height=30% width=30%  >
	<form action="findtownhouses" method="post">
		<h1>Search for a TownHouse by City</h1>
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
	<div id="townHouseCreate">
		<a href="townhousecreate">Create TownHouse</a>
	</div>
	<br />
	<h1>Matching TownHouses</h1>
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

			<th>HouseValue</th>
			<th>Furnished</th>

			<th>Delete TownHouse</th>
			<th>Update TownHouse</th>
		</tr>
		<c:forEach items="${townHouses}" var="townHouse">
			<tr>

				<td><c:out value="${townHouse.getHouseId()}" /></td>
				<td><c:out value="${townHouse.getStreet1()}" /></td>
				<td><c:out value="${townHouse.getStreet2()}" /></td>
				<td><c:out value="${townHouse.getCity()}" /></td>
				<td><c:out value="${townHouse.getState()}" /></td>
				<td><c:out value="${townHouse.getZip()}" /></td>
				<td><c:out value="${townHouse.getAvailability()}" /></td>
				<td><c:out value="${townHouse.getAvailableDate()}" /></td>
				<td><c:out value="${townHouse.getOwner().getOwnerId()}" /></td>
				<td><c:out value="${townHouse.getDescription()}" /></td>
				<td><c:out value="${townHouse.getFlooring()}" /></td>
				<td><c:out value="${townHouse.getBedrooms()}" /></td>
				<td><c:out value="${townHouse.getBathrooms()}" /></td>
				<td><c:out value="${townHouse.getDeposit()}" /></td>
				<td><c:out value="${townHouse.getRating()}" /></td>
				<td><c:out value="${townHouse.getPets()}" /></td>
				<td><c:out value="${townHouse.getParking()}" /></td>
				<td><c:out value="${townHouse.getLaundry()}" /></td>
				<td><c:out value="${townHouse.getYearOfBuilder()}" /></td>
				<td><c:out value="${townHouse.getRent()}" /></td>


				<td><c:out value="${townHouse.getHouseValue()}" /></td>
				<td><c:out value="${townHouse.getFurnished()}" /></td>

				<td><a
					href="townhousedelete?houseid=<c:out value="${townHouse.getHouseId()}"/>">Delete</a></td>
				<td><a
					href="townhouseupdate?houseid=<c:out value="${townHouse.getHouseId()}"/>">Update</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
