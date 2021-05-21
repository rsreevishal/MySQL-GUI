<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Create Table</title>
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
		<h1 class="display-4">Create a table</h1>
		<a href="/mysqlgui/Dashboard" class="btn btn-primary" role="button">Back</a>
		<hr class="my-4" />
		<form action="/mysqlgui/TableIndex" method="post">
			<div class="form-group">
				<label for="field1">Table name</label> <input class="form-control-sm"
					type="text" name="tablename" required id="field1" /><br />
			</div>
			<div class=form-group>
				<label for="fields">Fields</label>
				<ul class="list-group" id="fields"></ul>
			</div>
			<button class="btn btn-success" id="addField">Add field</button>
			<button class="btn btn-secondary" type="submit">Create table</button>
		</form>
	</div>
</body>
<script>
	var fieldCount = 0;
	function getCheckBox(id,label,value) {
		return `<div class="form-check form-check-inline"><input class="form-check-input" name="constraint" type="checkbox" value="${value}"/><label class="form-check-label" for="${id}">${label}</label></div>`
	}
	$(document).ready(() => {
		var fieldTemplate = `<li class="list-groupt-item"><div><input class="form-control-sm" type="text" required name="field"/><select required name="type"><option value="INT">INT</option><option value="VARCHAR">VARCHAR</option></select>${getCheckBox("pk", "PRIMARY KEY", fieldCount+"PRIMARY_KEY")}${getCheckBox("notnull", "NOT NULL", fieldCount+"NOT_NULL")}${getCheckBox("uniq", "UNIQUE", fieldCount+"UNIQUE")}${getCheckBox("none", "NONE", fieldCount+"NONE")}</div></li>`
		$("#fields").append(fieldTemplate);
		fieldCount += 1;
	});
	$("#addField").on('click', (e) => {
		e.preventDefault();
		var fieldTemplate = `<li class="list-groupt-item"><div><input class="form-control-sm" type="text" required name="field"/><select required name="type"><option value="INT">INT</option><option value="VARCHAR">VARCHAR</option></select>${getCheckBox("pk", "PRIMARY KEY", fieldCount+"PRIMARY_KEY")}${getCheckBox("notnull", "NOT NULL", fieldCount+"NOT_NULL")}${getCheckBox("uniq", "UNIQUE", fieldCount+"UNIQUE")}${getCheckBox("none", "NONE", fieldCount+"NONE")}</div></li>`
		$("#fields").append(fieldTemplate);
		fieldCount += 1;
	});
</script>
</html>