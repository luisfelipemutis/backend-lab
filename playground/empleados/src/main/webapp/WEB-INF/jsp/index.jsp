<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>

    <title>Sistema de empleados</title>

</head>
<body>

<div class="container">

    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/empleados">Sistema de Empleados</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/empleados">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/empleados/agregar">Agregar Empleado</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

</div>

<div class="container">
    <div class="text-center" style="margin: 30px;">
        <h3>Sistema de Empleados</h3>
    </div>

    <div class="container">
        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark text-center">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Departamento</th>
                <th scope="col">Sueldo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <th scope="row">${employee.idEmpleado}</th>
                    <td>${employee.nombre}</td>
                    <td>${employee.departamento}</td>
                    <td>
                        <fmt:setLocale value="es_US"/>
                        <fmt:formatNumber type="currency"
                                          value="${employee.sueldo}"/>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>


</body>
</html>