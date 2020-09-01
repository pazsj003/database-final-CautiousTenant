<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Credit</title>
</head>
<body>
<img src="https://cdn.houseplans.com/product/o2d2ui14afb1sov3cnslpummre/w1024.jpg?v=15" 
       alt="Smiley face" height=50% width=50%  >
	<form action="findcredit" method="post">
		<h1>Search for Credit by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Credit</h1>
        <table border="1">
            <tr>
                <th>Ssn</th>
                <th>CreditHistoryMonth</th>
                <th>CreditScore</th>
            </tr>
            <c:forEach items="${credits}" var="credit" >
                <tr>
                    <td><c:out value="${credit.getSsn()}" /></td>
                    <td><c:out value="${credit.getCreditHistoryMonth()}" /></td>
                    <td><c:out value="${credit.getCreditScore()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
