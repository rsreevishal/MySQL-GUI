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
						<form action="/mysqlgui/ViewAllTableRecord" method="post">
							<input type="hidden" value="${table.getId()}" name="tableid" />
							<button type="submit">View</button>
						</form>
					</div>
				<td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${tableRecords != null}">
	<h4>Retrieved records</h4>
	<table>
		<tr>
			<c:forEach items="${tableRecords.get(0).getFields()}" var="field">
				<th>${field.getFieldName()}</th>
			</c:forEach>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${tableRecords}" var="record">
			<tr>
				<form action="/mysqlgui/UpdateTableRecord" method="post">
						<input type="hidden" name="tableid" value="${tableid}"/>
						<input type="hidden" name="tablename" value="${record.getTablename()}"/>
						<input type="hidden" name="primarykey" value="${record.getKey().getKey()}"/>
						<input type="hidden" name="primaryid" value="${record.getKey().getValue()}"/>
					<c:forEach items="${record.getFields()}" var="field">
						<c:choose>
							<c:when test="${record.getKey().getKey() != field.getFieldName()}">
								<input type="hidden" value="${field.getFieldName()}" name="fieldName"/>
								<input type="hidden" value="${field.getFieldType()}" name="fieldType"/>
								<td><input type="text" value="${field.getFieldValue()}" name="fieldValue"/></td>
							</c:when>
							<c:otherwise>
								<td>${field.getFieldValue()}</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<td><button type="submit">Update</button></td>
				</form>
				<td>
					<form action="/mysqlgui/DeleteTableRecord" method="post">
						<input type="hidden" name="tableid" value="${tableid}"/>
						<input type="hidden" name="tablename" value="${record.getTablename()}"/>
						<input type="hidden" name="primarykey" value="${record.getKey().getKey()}"/>
						<input type="hidden" name="primaryid" value="${record.getKey().getValue()}"/>
						<button type="submit">Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>