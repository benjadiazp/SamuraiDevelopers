<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema de ayuda Apoderados</title>
</head>
<body>
	<form action="usuario" method=post>
		<table>
			<h3>Ingrese sus credenciales</h3><br>
			<h4>Usuario</h4>
			<input type="text" name="ingresoUsuario" placeholder="EJ:pablo"> <br>
			<h4>Contraseña</h4>
			<input type="text" name="ingresoContraseña" placeholder=""><br>
			<input type="submit" name="Ingresar" value="Ingresar">	
			
			<a href="url">olvido su contraseña?</a>		
		</table>	
	</form>
</body>
</html>