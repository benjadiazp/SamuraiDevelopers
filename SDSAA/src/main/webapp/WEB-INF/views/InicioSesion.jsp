<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Sistema de atenci�n a apoderados</title>
</head>
<body class="container">

	<form action=iniciarSesion method=post class="centered" modelAttribute="usuario">
			<h3 class="well " >Ingrese sus credenciales</h3><br>
			<h4>Usuario</h4>
			<div class="row ">
 	 		<div class="col-xs-3 centered">
			<input type="text" name="idUsuario" value="idUsuario" placeholder="EJ:pablo" class="form-control "> <br>
			</div>
			</div>
			<h4 >Contrase�a</h4>
			<div class="row ">
 	 		<div class="col-xs-3 centereds">
			<input type="password" name="claveUsuario" value="claveUsuario" placeholder="" class="form-control "><br>
			</div>
			</div>
			<input type="submit" name="Ingresar" value="Ingresar" class="btn btn-primary">
			<a href="url">Olvid� mi contrase�a</a>

	</form>

</body>
</html>
