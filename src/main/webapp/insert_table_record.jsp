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
<title>MySql GUI | Insert table records</title>
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
		<h1 class="display-4">Create Record</h1>
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
								<form class="form-inline" action="" method="post">
									<c:forEach items="${table.getFields()}" var="field">
										<c:if
											test="${!field.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)}">
											<div class="form-group mx-2">
												<label>${field.getName()}</label>
												<input class="form-control-sm" type="text" name="fieldValue" required />
											</div>
											<input type="hidden" name="fieldName"
												value="${field.getName()}" />
											<input type="hidden" name="fieldType"
												value="${field.getFieldType()}" />
										</c:if>
									</c:forEach>
									<input type="hidden" value="${table.getName()}"
										name="tablename" />
									<button class="btn btn-success" type="submit">Insert</button>
								</form>
							</div>
						<td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>