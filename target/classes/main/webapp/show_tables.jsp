<%@ page import="model.Table" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Show Tables</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</head>
<body>
<div class="container text-center">
<h1 class="display-4">Tables</h1>
<a href="/mysqlgui/Dashboard" class="btn btn-primary" role="button">Back</a>
<hr class="my-4"/>
<table class="table">
<thead>
<tr>
	<th scope="col">Table name</th>
	<th scope="col"># of columns</th>
	<th scope="col">Action</th>
</tr>
</thead>
<tbody>
<c:forEach items="${tables}" var="table">
<tr>
	<td>${table.getName()}</td>
	<td>${table.getFields().size()}</td>
	<td>
		<form action="/mysqlgui/TableDetails" method="post">
			<input type="hidden" value="${table.getId()}" name="tableid"/>
			<button class="btn btn-secondary" type="submit">View</button>
		</form>
	<td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</body>
</html>