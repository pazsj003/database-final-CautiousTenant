<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a TownHouse</title>
</head>
<body>
	<h1>Create TownHouse</h1>
	<form action="townhousecreate" method="post">
		<p>
			<label for="street1">Street1</label>
			<input id="street1" name="street1" value="">
		</p>
		<p>
			<label for="street2">Street2</label>
			<input id="street2" name="street2" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>		
		<p>
			<label for="zip">Zip</label>
			<input id="zip" name="zip" value="">
		</p>	
		<p>
			<label for="availability">Availibility (true or false)</label>
			<input id="availability" name="availability" value="">
		</p>	
		<p>
			<label for="availabledate">AvailableDate</label>
			<input id="availabledate" name="availabledate" value="">
		</p>				
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>	
		<p>
			<label for="description">Descriptioin</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="flooring">Flooring (number)</label>
			<input id="flooring" name="flooring" value="">
		</p>
		<p>
			<label for="bedrooms">Bedrooms (number)</label>
			<input id="bedrooms" name="bedrooms" value="">
		</p>
		<p>
			<label for="bathrooms">Bathrooms (number)</label>
			<input id="bathrooms" name="bathrooms" value="">
		</p>
		<p>
			<label for="deposit">Deposit (number)</label>
			<input id="deposit" name="deposit" value="">
		</p>
		<p>
			<label for="rating">Rating (float number)</label>
			<input id="rating" name="rating" value="">
		</p>		
		<p>
			<label for="pets">Pets (true or false)</label>
			<input id="pets" name="pets" value="">
		</p>
		<p>
			<label for="parking">Parking (true or false)</label>
			<input id="parking" name="parking" value="">
		</p>
		<p>
			<label for="laundry">Laundry (true or false)</label>
			<input id="laundry" name="laundry" value="">
		</p>
		<p>
			<label for="yearOfBuild">YearOfBuild (number)</label>
			<input id="yearOfBuild" name="yearOfBuild" value="">
		</p>												
		<p>
			<label for="rent">Rent (number)</label>
			<input id="rent" name="rent" value="">
		</p>	
		<p>
			<label for="houseValue">HouseValue (number)</label>
			<input id="houseValue" name="houseValue" value="">
		</p>
		<p>
			<label for="furnished">Furnished (true or false)</label>
			<input id="furnished" name="furnished" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>