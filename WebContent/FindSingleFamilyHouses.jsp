<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a SingleFamilyHouse</title>
</head>
<body>
<img src="https://www.lennar.com/images/com/images/new-homes/9/129/3753/mhi/1200x650.jpg?w=1200&h=650&as=1" 
       alt="Smiley face" height=30% width=30%  >

	<form action="findsinglefamilyhouses" method="post">
		<h1>Search for a singleFamilyHouse by City</h1>
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
	<div id="singleFamilyHousecreate">
		<a href="singlefamilyhousecreate">Create SingleFamilyHouse</a>
	</div>
	<br />
	<h1>Matching singleFamilyHouses</h1>
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

			<th>Delete singleFamilyHouse</th>
			<th>Update singleFamilyHouse</th>
		</tr>
		<c:forEach items="${singleFamilyHouses}" var="singleFamilyHouse">
			<tr>
				<td><c:out value="${singleFamilyHouse.getHouseId()}" /></td>
				<td><c:out value="${singleFamilyHouse.getStreet1()}" /></td>
				<td><c:out value="${singleFamilyHouse.getStreet2()}" /></td>
				<td><c:out value="${singleFamilyHouse.getCity()}" /></td>
				<td><c:out value="${singleFamilyHouse.getState()}" /></td>
				<td><c:out value="${singleFamilyHouse.getZip()}" /></td>
				<td><c:out value="${singleFamilyHouse.getAvailability()}" /></td>
				<td><c:out value="${singleFamilyHouse.getAvailableDate()}" /></td>
				<td><c:out value="${singleFamilyHouse.getOwner().getOwnerId()}" /></td>
				<td><c:out value="${singleFamilyHouse.getDescription()}" /></td>
				<td><c:out value="${singleFamilyHouse.getFlooring()}" /></td>
				<td><c:out value="${singleFamilyHouse.getBedrooms()}" /></td>
				<td><c:out value="${singleFamilyHouse.getBathrooms()}" /></td>
				<td><c:out value="${singleFamilyHouse.getDeposit()}" /></td>
				<td><c:out value="${singleFamilyHouse.getRating()}" /></td>
				<td><c:out value="${singleFamilyHouse.getPets()}" /></td>
				<td><c:out value="${singleFamilyHouse.getParking()}" /></td>
				<td><c:out value="${singleFamilyHouse.getLaundry()}" /></td>
				<td><c:out value="${singleFamilyHouse.getYearOfBuilder()}" /></td>
				<td><c:out value="${singleFamilyHouse.getRent()}" /></td>
				
				<td><c:out value="${singleFamilyHouse.getHouseValue()}" /></td>
				<td><c:out value="${singleFamilyHouse.getFurnished()}" /></td>
				
				<td><a
					href="singlefamilyhousedelete?houseid=<c:out value="${singleFamilyHouse.getHouseId()}"/>">Delete</a></td>
				<td><a
					href="singlefamilyhouseupdate?houseid=<c:out value="${singleFamilyHouse.getHouseId()}"/>">Update</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
