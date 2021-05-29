<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySql GUI | Crudql</title>
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
		<div style="background-color: transparent !important;">
			<h1 class="display-4">CrudQL</h1>
			<p class="lead">A CRUD Query Language for MySql DB.</p>
			<a href="/mysqlgui/Dashboard" class="btn btn-primary" role="button">Back</a>
			<button class="btn btn-info" type="button" onClick="showSyntax()">Syntax</button>
			<hr class="my-4">
			<p class="lead">
			<ol class="list-group" style="display: none;" id="syntaxList">
				<li class="list-group-item"><span style="color: #4287f5;">tablename
						= </span> To view all data in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename
						= id</span> To view data with primary key(id) in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename
						+ ('val1','val2', ...) </span> To add data in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename
						^ (id,'val1','val2', ...) </span> To update data with primary key(id) in
					the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename
						- id</span> To delete data with primary key(id) in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename.colname
						= </span> To view all colname data in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">tablename.colname
						= 1 </span> To view colname data with primary key(id) in the table</li>
				<li class="list-group-item"><span style="color: #4287f5;">$var
						= (tablename.colname = 1) </span> To store the value of returned data</li>
				<li class="list-group-item"><span style="color: #4287f5;">table
						+= ('$var','val1'...) </span> To add stored data</li>
			</ol>
			</p>
		</div>
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link ${ tab1 }" id="editor-tab" data-toggle="tab"
					data-target="#editor" type="button" role="tab" aria-controls="editor"
					aria-selected="true">Editor</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link ${ tab2 }" id="app-tab" data-toggle="tab"
					data-target="#app" type="button" role="tab"
					aria-controls="app" aria-selected="false">App</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link ${ tab3 }" id="query-tab" data-toggle="tab"
					data-target="#query" type="button" role="tab"
					aria-controls="contact" aria-selected="false">Query</button>
			</li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show ${ tab1 }" id="editor" role="tabpanel"
				aria-labelledby="editor-tab">
				<form class="form" action="/mysqlgui/Crudql" method="post">
					<div class="form-group">
						<label for="query">Editor</label>
						<textarea rows="20" class="form-control"
							placeholder="Create and view form here and view the result in App tab.." name="query"
							id="editor_query" required></textarea>
					</div>
					<input type="hidden" name="tab" value="tab1"/>
					<button type="submit" class="btn btn-success">Execute</button>
				</form>
				<c:choose>
					<c:when test="${queryResult != null}">
						${queryResult}
					</c:when>
					<c:otherwise>
						<p>Run some query</p>
					</c:otherwise>
				</c:choose>
				</div>
			<div class="tab-pane fade show ${ tab2 }" id="app" role="tabpanel"
				aria-labelledby="app-tab">
				${form}
				<p id="table_form_result"></p>
				</div>
			<div class="tab-pane fade show ${ tab3 }" id="query" role="tabpanel"
				aria-labelledby="query-tab">
				<form class="form" action="/mysqlgui/Crudql" method="post">
					<div class="form-group">
						<label for="query">Query</label>
						<textarea class="form-control"
							placeholder="Separate each query by new line" name="query"
							id="query" required></textarea>
					</div>
					<input type="hidden" name="tab" value="tab3"/>
					<button type="submit" class="btn btn-success">Execute</button>
				</form>
				<h3>Result</h3>
				<c:choose>
					<c:when test="${queryResult != null}">
						${queryResult}
					</c:when>
					<c:otherwise>
						<p>Run some query</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>



	</div>
</body>
<script>
	function showSyntax() {
		var curD = $("#syntaxList").css("display");
		if (curD === "none") {
			$("#syntaxList").css("display", "block");
		}
		if (curD === "block") {
			$("#syntaxList").css("display", "none");
		}
	}
	$("#table_form").submit(function(e) {
        e.preventDefault();
        var form = $(this);
        var url = form.attr('action');
        $.ajax({
           type: "POST",
           url: url,
           data: form.serialize(),
           success: function(data)
           {
               $("#table_form_result").html("<span style='color: green;'>Successfully inserted!</span>")
           },
           error: function(data) {
        	   $("#table_form_result").html("<span style='color: red;'>Failed to insert!</span>")
           }
        });
    });
	$("#editor-tab").on('click', function() {
		$(".tab1").show();
		$(".tab3").hide();
	});
	$("#query-tab").on('click', function() {
		$(".tab1").hide();
		$(".tab3").show();
	});
</script>
</html>