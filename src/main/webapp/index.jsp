<html>
<head>
<title>MySql GUI | Index</title>
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
		<div class="jumbotron" style="background-color:transparent !important;">
			<h1 class="display-4">MySql GUI</h1>
			<p class="lead">A Graphical User Interface for MySql DB.</p>
			<hr class="my-4">
			<p class="lead"><form action="/mysqlgui/Auth" method="post">
				<div class="form-group">
		<label for="field1">Username</label>
		<input class="form-control-sm" id="field1" type="text" name="username"
						required /><br />
	</div>
	<div class="form-group">
		<label for="field2">Password</label>
		<input class="form-control-sm" id="field2" type="password"
						name="password" required /><br />	
	</div>
	<button class="btn btn-primary" type="submit">Login</button>
</form></p>
			
</div>


</div>
</body>
</html>
