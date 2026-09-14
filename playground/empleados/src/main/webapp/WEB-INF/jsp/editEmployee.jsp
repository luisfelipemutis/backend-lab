<%@ include file="common/header.jsp" %>

<%@ include file="common/navbar.jsp" %>

<div class="container">
    <div class="text-center" style="margin: 30px;">
        <h3>Editar Empleado</h3>
    </div>

    <!-- Concepto de spring mvc-->
    <form action="${urlEditar}" modelAttribute="employeeForm" method="post">
        <input type="hidden" name="idEmpleado" value="${employee.idEmpleado}"/>
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre"
                name="nombre" required="true" value="${employee.nombre}">
        </div>
        <div class="mb-3">
            <label for="departamento" class="form-label">Departamento</label>
            <input type="text" class="form-control" id="departamento"
                name="departamento" value="${employee.departamento}">
        </div>
        <div class="mb-3">
            <label for="sueldo" class="form-label">Sueldo</label>
            <input type="number" step="any" class="form-control" id="sueldo"
                name="sueldo" value="${employee.sueldo}">
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-warning btn-sm me-3">Editar</button>
            <a href="${urlInicio}" class="btn btn-danger btn-sm">Regresar</a>
        </div>

    </form>

</div>


<%@ include file="common/footer.jsp" %>
