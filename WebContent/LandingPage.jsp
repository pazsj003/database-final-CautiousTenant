<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cautious Tenant</title>
</head>
<body>
    <img src="https://static.dezeen.com/uploads/2017/08/clifton-house-project-architecture_dezeen_hero-1.jpg" 
    alt="Smiley face" height=30% width=30%  >
	<form action="home" method="post">
	    
		<h1>Search for a User by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="findcheapesthouses"><a href="findcheapesthouses">Find Cheapest Houses In The City</a></div>
	
	<br/>
	<div id="ownerCreate"><a href="ownercreate">Create Owner</a></div>
	
	<br/>
	<div id="tenantCreate"><a href="tenantcreate">Create Tenant</a></div>
	<br/>
	
	<div id="townhousecreate"><a href="townhousecreate">Create TownHouse</a></div>
	<br/>
	
	<div id="findtownhouses"><a href="findtownhouses">Find TownHouse</a></div>
	<br/>
	
	<div id="findapartments"><a href="findapartments">Find Apartment</a></div>
	<br/>
	
	<div id="findsinglefamilyhouses"><a href="findsinglefamilyhouses">Find Single Family Houses</a></div>
	<br/>
	
	
	
	
	
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Age</th>
                <th>Delete User</th>
                <th>Update User</th>
                <th>Credit</th>
                <th>Reviews</th>
                <th>Recommendations</th>
                <th>Likes</th>
                <th>Reservations</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><c:out value="${user.getPhone()}" /></td>
                    <td><c:out value="${user.getAge()}" /></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
                    <td><a href="findcredit?username=<c:out value="${user.getUserName()}"/>">Credit</a></td>
                    <td><a href="reviewread?username=<c:out value="${user.getUserName()}"/>">Reviews</a></td>
                    <td><a href="recommendationread?username=<c:out value="${user.getUserName()}"/>">Recommendations</a></td>
                    <td><a href="likeread?username=<c:out value="${user.getUserName()}"/>">Likes</a></td>
                    <td><a href="reservationread?username=<c:out value="${user.getUserName()}"/>">Reservations</a></td>
                    
                    
                </tr>
            </c:forEach>
       </table>
</body>
</html>
