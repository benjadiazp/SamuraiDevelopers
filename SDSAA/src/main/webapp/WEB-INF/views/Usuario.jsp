<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Sistema de atención de apoderados</title>
</head>
<body class="container">

	<form action="usuario" method=post class="centered">
			<h3 class="well " >Ingrese sus credenciales</h3><br>
			<h4>Usuario</h4>
			<div class="row ">
 	 		<div class="col-xs-3 centered">
			<input type="text" name="ingresoUsuario" placeholder="EJ:pablo" class="form-control "> <br>
			</div>
			</div>
			<h4 >Contraseña</h4>
			<div class="row ">
 	 		<div class="col-xs-3 centereds">
			<input type="password" name="ingresoContrase�a" placeholder="" class="form-control "><br>
			</div>
			</div>
			<input type="submit" name="Ingresar" value="Ingresar" class="btn btn-primary">
			<a href="url">Olvidé mi contraseña</a>

	</form>

</body>
</html>
