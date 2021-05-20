<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h1>Create a table</h1>
<form action="/mysqlgui/Dashboard" method="get">
	<button type="submit">Back</button>
</form>
<form action="/mysqlgui/TableIndex" method="post">
	<label for="field1">Table name</label>
	<input type="text" name="tablename" required id="field1"/><br/>
	<label for="fields">Fields</label>
	<ol id="fields"></ol>
	<button id="addField">Add field</button>
	<input type="submit" value="Create table"/>
</form>
</body>
<script>
	var fieldCount = 0;
	$(document).ready(() => {
		var fieldTemplate = `<li><div><input type="text" required name="field"/><select required name="type"><option value="INT">INT</option><option value="VARCHAR">VARCHAR</option></select><label>Primary key</label><input type="checkbox" name="constraint" value="${fieldCount}PRIMARY_KEY"/><label>Not null</label><input type="checkbox" name="constraint" value="${fieldCount}NOT_NULL"/><label>UNIQUE</label><input type="checkbox" name="constraint" value="${fieldCount}UNIQUE"/><label>None</label><input type="checkbox" name="constraint" value="${fieldCount}NONE" checked/></div></li>`
		$("#fields").append(fieldTemplate);
		fieldCount += 1;
	});
	$("#addField").on('click', (e) => {
		e.preventDefault();
		var fieldTemplate = `<li><div><input type="text" required name="field"/><select required name="type"><option value="INT">INT</option><option value="VARCHAR">VARCHAR</option></select><label>Primary key</label><input type="checkbox" name="constraint" value="${fieldCount}PRIMARY_KEY"/><label>Not null</label><input type="checkbox" name="constraint" value="${fieldCount}NOT_NULL"/><label>UNIQUE</label><input type="checkbox" name="constraint" value="${fieldCount}UNIQUE"/><label>None</label><input type="checkbox" name="constraint" value="${fieldCount}NONE" checked/></div></li>`
		$("#fields").append(fieldTemplate);
		fieldCount += 1;
	});
</script>
</html>