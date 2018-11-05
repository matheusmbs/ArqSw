<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<form action="logar" method="post">
					<div class="form-group">
						<input type="text" name="username" id="username" />
					</div>
					<div class="form-group">
						<input type="text" name="password" id="password" />
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Logar</button>
					</div>
					

				</form>
			</div>
		</div>
	</div>
	<script>
		if(${login} == "1"){
			alert("Usuario e senha invalido")
		}
	</script>
</body>
</html>