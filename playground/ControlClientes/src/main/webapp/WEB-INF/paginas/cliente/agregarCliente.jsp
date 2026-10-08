<!--agregarClienteModal debe ser el mismo nombre definido en el archivo navbar.jsp para poder abrirlo cuando se presione el boton.-->
<div class="modal fade" id="agregarClienteModal" tabindex="-1" aria-labelledby="agregarClienteLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title" id="agregarClienteLabel">Agregar Cliente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!--Formulario para adicionar un nuevo cliente-->
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group mb-3">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" required/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="imei">Email</label>
                        <input type="imei" class="form-control" id="imei" name="imei" required/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="telefono">Telefono</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" required/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="saldo">Saldo</label>
                        <input type="number" class="form-control" id="saldo" name="saldo" required
                               step="any"/>
                    </div>
                </div>
                <!--Pie de pagina de ventana modal-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>