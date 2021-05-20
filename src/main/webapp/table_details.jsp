<%@ page import="model.Table" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | table details</title>
</head>
<body>
<h1>Table details</h1>
<h3>Table name: <c:out value="${table.getName()}" /></h3>
<table>
	<tr>
		<th>Column</th>
		<th>Constraints</th>
	</tr>
	<c:forEach items="${table.getFields()}" var="field">
		<tr>
			<td>${field.getName()}</td>
			<td>${field.getFieldConstraintString()}</td>
		</tr>
	</c:forEach>
</table>
<form action="/mysqlgui/DeleteTable" method="post">
	<input type="hidden" value="${table.getName()}" name="tablename"/>
	<input type="hidden" value="${table.getId()}" name="tableid"/>
	<button type="submit">Delete table</button>
</form>
<form action="/mysqlgui/Dashboard" method="get">
	<button type="submit">Back</button>
</form>
</body>
</html>