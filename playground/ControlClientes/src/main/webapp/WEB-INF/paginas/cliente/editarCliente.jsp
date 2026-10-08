<!doctype html>
<html data-bs-theme="dark">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Editar Cliente</title>
        <!--Bootstrap css-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <!--Bootstrap icons-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <!--Cabecero-->
        <jsp:include page="/WEB-INF/paginas/templates/header.jsp"/>

        <!--Formulario para editar cliente-->
        <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.idCliente}"
              method="POST" class="was-validated">

            <!--Botones de navegacion de edicion cliente-->
            <jsp:include page="/WEB-INF/paginas/templates/navbarNavigation.jsp"/>

            <div class="container">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4>Editar Cliente</h4>
                    </div>
                    <div class="card-body">
                        <div class="form-group mb-3">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required
                                   value="${cliente.nombre}"/>
                        </div>
                        <div class="form-group mb-3">
                            <label for="apellido">Apellido</label>
                            <input type="text" class="form-control" id="apellido" name="apellido" required
                                   value="${cliente.apellido}"/>
                        </div>
                        <div class="form-group mb-3">
                            <label for="imei">Email</label>
                            <input type="imei" class="form-control" id="imei" name="imei" required
                                   value="${cliente.imei}"/>
                        </div>
                        <div class="form-group mb-3">
                            <label for="telefono">Telefono</label>
                            <input type="tel" class="form-control" id="telefono" name="telefono" required
                                   value="${cliente.telefono}"/>
                        </div>
                        <div class="form-group mb-3">
                            <label for="saldo">Saldo</label>
                            <input type="number" class="form-control" id="saldo" name="saldo" required
                                   step="any" value="${cliente.saldo}"/>
                        </div>

                        <button type="submit" class="btn btn-success">Guardar Cambios</button>
                    </div>
                </div>
            </div>

        </form>
        <!--pie de pagina-->
        <jsp:include page="/WEB-INF/paginas/templates/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>