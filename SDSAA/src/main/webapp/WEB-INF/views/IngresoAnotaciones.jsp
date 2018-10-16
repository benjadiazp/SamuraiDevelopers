<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Ingresar anotaciones</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css">
</head>

<body>
    <div class="card">
        <div class="card-header">
            <h3 class="mb-0">Sistema de atención de apoderados<button class="btn btn-secondary active btn-sm float-right" type="button">Cerrar Sesión</button></h3>
        </div>
    </div>
    <div id="wrapper">
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li> <a href="#">Notificaciones</a></li>
                <li> <a href="#">Calendario de Evaluaciones</a>
                <a href="#">Calificaciones</a>
                <a href="#">Anotaciones</a>
                <a href="#">Asistencia&nbsp;</a>
                <a href="#">Justificativos&nbsp;</a>
                <a href="#">Mensajes&nbsp;</a></li>
            </ul>
        </div>
        <div class="page-content-wrapper">
            <div class="container-fluid"><a class="btn btn-link" role="button" href="#menu-toggle" id="menu-toggle"><i class="fa fa-bars"></i></a>
                <div class="row">
                    <div class="col-md-12">
                        <div>
                            <h1>Ingresar anotación</h1>
                        </div>
                        <p></p>
                    </div>
                    <div class="col-md-12 offset-md-0">
                        <div></div><label>Estudiante:</label>
                        <div class="input-group">
                            <div class="input-group-prepend"><span class="input-group-text">Nombre</span></div><input class="form-control" type="text">
                            <div class="input-group-append"><button class="btn btn-primary" type="button" class="Buscar" name="Buscar">Buscar</button></div>
                        </div><label class="float-left">Tipo:</label><select><option value="12" selected="">Positiva</option><option value="13">Negativa</option></select>
                        <div></div>
                    </div>
                    <div class="col-md-12 offset-md-0">
                        <div></div>
                        <div class="input-group">
                            <div class="input-group-prepend"></div>
                            <div class="input-group-append"><button class="btn btn-dark" type="submit" class="Agregar" name="Agregar">Agregar</button></div>
                        </div>
                        <div></div>
                    </div>
                    <div class="col-md-12 offset-md-0">
                        <div></div>
                        <div class="input-group">
                            <div class="input-group-prepend"></div>
                            <div class="input-group-append"></div>
                        </div>
                        <div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/script.min.js"></script>
</body>

</html>
