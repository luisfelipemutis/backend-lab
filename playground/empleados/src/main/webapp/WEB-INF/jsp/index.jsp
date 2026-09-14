<%@ include file="common/header.jsp" %>

<%@ include file="common/navbar.jsp" %>

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
                <th scope="col">Acciones</th>
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
                    <td class="text-center">
                        <c:set var="urlEdit">
                            <c:url value="${application.contextPath}/editar">
                                <c:param name="idEmpleado"
                                         value="${employee.idEmpleado}"/>
                            </c:url>
                        </c:set>
                        <a href="${urlEdit}" class="btn btn-warning btn-sm me-3">
                            Editar
                        </a>

                        <c:set var="urlDelete">
                            <c:url value="${application.contextPath}/eliminar">
                                <c:param name="idEmpleado"
                                         value="${employee.idEmpleado}"/>
                            </c:url>
                        </c:set>
                        <a href="${urlDelete}" class="btn btn-danger btn-sm me-3">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>


<%@ include file="common/footer.jsp" %>