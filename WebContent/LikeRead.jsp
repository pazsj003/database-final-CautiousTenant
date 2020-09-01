<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Likes</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>LikeId</th>
                <th>Liked</th>
                <th>HouseId</th>
                <th>UserName</th>
                <th>Delete Like</th>
            </tr>
            <c:forEach items="${likes}" var="like" >
                <tr>
                    <td><c:out value="${like.getLikeId()}" /></td>
                     <td><c:out value="${like.isLiked()}" /></td>
                    <td><c:out value="${like.getHouse().getHouseId()}"/></td>
                    <td><a href="findusers?username=<c:out value="${like.getUsers().getUserName()}"/>">${like.getUsers().getUserName()}</a></td>
                    <td><a href="likedelete?likeid=<c:out value="${like.getLikeId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>