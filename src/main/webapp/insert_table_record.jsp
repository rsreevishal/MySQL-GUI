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
</head>
<body>
	<h1>Tables</h1>
	<form action="/mysqlgui/Dashboard" method="get">
		<button type="submit">Back</button>
	</form>
	<table>
		<tr>
			<th>Table</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${tables}" var="table">
			<tr>
				<td>${table.getName()}</td>
				<td>
					<div id="table_${table.getId()}">
						<form action="" method="post">
							<c:forEach items="${table.getFields()}" var="field">
								<c:if test="${!field.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)}">
									<label>${field.getName()}</label>
									<input type="hidden" name="fieldName"
										value="${field.getName()}" />
									<input type="hidden" name="fieldType"
										value="${field.getFieldType()}" />
									<input type="text" name="fieldValue" required />
								</c:if>
							</c:forEach>
							<input type="hidden" value="${table.getName()}" name="tablename" />
							<button type="submit">Insert</button>
						</form>
					</div>
				<td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>