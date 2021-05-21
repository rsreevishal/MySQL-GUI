<%@ page import="model.Table"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Table Details</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
	integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container text-center">
		<h1 class="display-4">Table details</h1>
		<a class="btn btn-primary" href="/mysqlgui/Dashboard" role="button">Back</a>
		<hr class="my-4">
		<h3>
			Table name:
			<c:out value="${table.getName()}" />
		</h3>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Column</th>
					<th scope="col">Type</th>
					<th scope="col">Constraints</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${table.getFields()}" var="field">
					<tr>
						<td>${field.getName()}</td>
						<td>${field.getFieldType()}</td>
						<td>${field.getFieldConstraintString()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="/mysqlgui/DeleteTable" method="post">
			<input type="hidden" value="${table.getName()}" name="tablename" /> <input
				type="hidden" value="${table.getId()}" name="tableid" />
			<button class="btn btn-danger" type="submit">Delete table</button>
		</form>
	</div>

</body>
</html>