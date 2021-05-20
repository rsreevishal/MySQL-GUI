<%@ page import="model.Table" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | show tables</title>
</head>
<body>
<h1>Tables</h1>
<form action="/mysqlgui/Dashboard" method="get">
	<button type="submit">Back</button>
</form>
<table>
<tr>
	<th>Table name</th>
	<th># of columns</th>
	<th>Action</th>
</tr>
<c:forEach items="${tables}" var="table">
<tr>
	<td>${table.getName()}</td>
	<td>${table.getFields().size()}</td>
	<td>
		<form action="/mysqlgui/TableDetails" method="post">
			<input type="hidden" value="${table.getId()}" name="tableid"/>
			<button type="submit">View</button>
		</form>
	<td>
</tr>
</c:forEach>
</table>
</body>
</html>