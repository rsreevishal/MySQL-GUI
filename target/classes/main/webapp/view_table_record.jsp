<%@ page import="model.Table"%>
<%@ page import="model.FieldConstraint"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | View Table Record</title>
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
		<h1 class="display-4">Table Records</h1>
		<a href="/mysqlgui/Dashboard" class="btn btn-primary" role="button">Back</a>
		<hr class="my-4" />
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Table</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tables}" var="table">
					<tr>
						<td>${table.getName()}</td>
						<td>
							<div id="table_${table.getId()}">
								<form action="/mysqlgui/ViewAllTableRecord" method="post">
									<input type="hidden" value="${table.getId()}" name="tableid" />
									<button class="btn btn-secondary" type="submit">View</button>
								</form>
							</div>
						<td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<hr class="my-4" />
		<c:choose>
			<c:when test="${tableRecords != null && tableRecords.size() > 0}">
			<h4>Retrieved records</h4>
			<table class="table">
				<thead>
					<tr>
						<c:forEach items="${tableRecords.get(0).getFields()}" var="field">
							<th scope="col">${field.getFieldName()}</th>
						</c:forEach>
						<th scope="col">Update</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tableRecords}" var="record">
						<tr>
							<form class="form-inline" action="/mysqlgui/UpdateTableRecord"
								method="post">
								<input type="hidden" name="tableid" value="${tableid}" /> <input
									type="hidden" name="tablename" value="${record.getTablename()}" />
								<input type="hidden" name="primarykey"
									value="${record.getKey().getKey()}" /> <input type="hidden"
									name="primaryid" value="${record.getKey().getValue()}" />
								<c:forEach items="${record.getFields()}" var="field">
									<c:choose>
										<c:when
											test="${record.getKey().getKey() != field.getFieldName()}">
											<input type="hidden" value="${field.getFieldName()}"
												name="fieldName" />
											<input type="hidden" value="${field.getFieldType()}"
												name="fieldType" />
											<td><input class="form-control-sm" type="text"
												value="${field.getFieldValue()}" name="fieldValue" /></td>
										</c:when>
										<c:otherwise>
											<td>${field.getFieldValue()}</td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							<td><button class="btn btn-info" type="submit">Update</button></td>
							</form>
							<td>
								<form action="/mysqlgui/DeleteTableRecord" method="post">
									<input type="hidden" name="tableid" value="${tableid}" /> <input
										type="hidden" name="tablename"
										value="${record.getTablename()}" /> <input type="hidden"
										name="primarykey" value="${record.getKey().getKey()}" /> <input
										type="hidden" name="primaryid"
										value="${record.getKey().getValue()}" />
									<button class="btn btn-danger" type="submit">Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:when>
			<c:otherwise>
				<p class="h4">No records</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>