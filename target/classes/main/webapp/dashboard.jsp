<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Dashboard</title>
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
		<h1 class="display-4">DashBoard</h1>
		<hr class="my-4">
		<ul class="list-group">
			<li class="list-group-item">
				<h2>DML Queries</h2>
				<div class="list-group list-group-flush">
					<a class="list-group-item list-group-item-action"
						href="/mysqlgui/ShowTables">Show/Delete tables </a> <a
						class="list-group-item list-group-item-action"
						href="/mysqlgui/TableIndex">Create table </a>
				</div>
			</li>
			<li class="list-group-item">
				<h2>DDL Queries</h2>
				<div class="list-group list-group-flush">
					<a class="list-group-item list-group-item-action"
						href="/mysqlgui/ViewAllTableRecord">Show/Update/Delete record
					</a> <a class="list-group-item list-group-item-action"
						href="/mysqlgui/InsertTableRecord">Create record </a>

				</div>
			</li>
		</ul>
	</div>
</body>
</html>