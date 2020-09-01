<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Apartment</title>
</head>
<body>
  <img src="https://g5-assets-cld-res.cloudinary.com/image/upload/q_auto,f_auto,fl_lossy/g5/g5-c-ils8pxf8-sequoia-equities-client/g5-cl-563e34wz7-brio-apartment-homes/uploads/hero-brio.jpg" 
       alt="Smiley face" height=30% width=30%  >
	<form action="findapartments" method="post">
		<h1>Search for a Apartment by City</h1>
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
	<div id="apartmentcreate">
		<a href="apartmentcreate">Create apartment</a>
	</div>
	<br />
	<h1>Matching apartments</h1>
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
			<th>ApartmentName</th>
			<th>Capacity</th>
		</tr>
		<c:forEach items="${apartments}" var="apartment">
			<tr>
			
			<td><c:out value="${apartment.getHouseId()}" /></td>
				<td><c:out value="${apartment.getStreet1()}" /></td>
				<td><c:out value="${apartment.getStreet2()}" /></td>
				<td><c:out value="${apartment.getCity()}" /></td>
				<td><c:out value="${apartment.getState()}" /></td>
				<td><c:out value="${apartment.getZip()}" /></td>
				<td><c:out value="${apartment.getAvailability()}" /></td>
				<td><c:out value="${apartment.getAvailableDate()}" /></td>
				<td><c:out value="${apartment.getOwner().getOwnerId()}" /></td>
				<td><c:out value="${apartment.getDescription()}" /></td>
				<td><c:out value="${apartment.getFlooring()}" /></td>
				<td><c:out value="${apartment.getBedrooms()}" /></td>
				<td><c:out value="${apartment.getBathrooms()}" /></td>
				<td><c:out value="${apartment.getDeposit()}" /></td>
				<td><c:out value="${apartment.getRating()}" /></td>
				<td><c:out value="${apartment.getPets()}" /></td>
				<td><c:out value="${apartment.getParking()}" /></td>
				<td><c:out value="${apartment.getLaundry()}" /></td>
				<td><c:out value="${apartment.getYearOfBuilder()}" /></td>
				<td><c:out value="${apartment.getRent()}" /></td>
				<td><c:out value="${apartment.getApartmentName()}" /></td>
				<td><c:out value="${apartment.getCapacity()}" /></td>

			
			</tr>
		</c:forEach>
	</table>
</body>
</html>
