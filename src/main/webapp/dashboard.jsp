<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI</title>
</head>
<body>
<h1>DashBoard</h1>
<ul>
	<li>
		<h2>DML Queries</h2>
		<ol>
			<li>
				<form action="/mysqlgui/ShowTables" method="get">
					<button type="submit">Show/Delete tables</button>
				</form>
			</li>
			<li>
				<form action="/mysqlgui/TableIndex" method="get">
					<button type="submit">Create table</button>
				</form>
			</li>
		</ol>
	</li>
	<li>
		<h2>DDL Queries</h2>
			<ol>
				<li>
					<form action="" method="get">
						<button type="submit">View record</button>
					</form>
				</li>
				<li>
					<form action="" method="get">
						<button type="submit">Insert record</button>
					</form>
				</li>
				<li>
					<form action="" method="get">
						<button type="submit">Update record</button>
					</form>
				</li>
				<li>
					<form action="" method="get">
						<button type="submit">Delete record</button>
					</form>
				</li>
			</ol>
	</li>
</ul>
</body>
</html>