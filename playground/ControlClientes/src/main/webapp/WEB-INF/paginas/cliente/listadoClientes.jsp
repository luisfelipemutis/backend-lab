<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Libreria para campo saldo-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--formato a la moneda para que el formato numerico no falle-->
<fmt:setLocale value="es_CO"/>

<!--listado clientes-->
<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Saldo</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <!--Iteracion de los clientes con tagLib-->
                        <tbody>
                            <c:forEach var="client" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${client.nombre} ${client.apellido}</td>
                                    <td><fmt:formatNumber value="${client.saldo}"
                                                      type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${client.idCliente}"
                                           class="btn btn-secondary btn-sm">
                                            <i class="bi bi-pencil"></i>Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${client.idCliente}"
                                           class="btn btn-danger btn-sm">
                                            <i class="bi bi-trash"></i>Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--Totales-->
            <div class="col-md-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo Total</h3>
                        <h4 class="display-6">
                            <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-6">
                            <i class="bi bi-people"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>