<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>ReservationId</th>
                <th>Start</th>
                <th>End</th>
                <th>Size</th>
                <th>HouseId</th>
                <th>UserName</th>
                <th>Delete Reservation</th>
            </tr>
            <c:forEach items="${reservations}" var="reservation" >
                <tr>
                    <td><c:out value="${reservation.getReservationId()}" /></td>
                     <td><fmt:formatDate value="${reservation.getStart()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                      <td><fmt:formatDate value="${reservation.getEnd()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                     <td><c:out value="${reservation.getSize()}"/></td>
                   
                    <td><c:out value="${reservation.getHouses().getHouseId()}"/></td>
                    <td><a href="findusers?username=<c:out value="${reservation.getTenants().getUserName()}"/>">${reservation.getTenants().getUserName()}</a></td>
                    <td><a href="reservationdelete?reservationid=<c:out value="${reservation.getReservationId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>