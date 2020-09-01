<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	

	
	
	
	
	
	<h1>${messages.title}</h1>
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
