package fm.empleados.service;

import fm.empleados.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> getEmpleados();

    Empleado getEmpleadoById(Integer id);

    void saveEmpleado(Empleado empleado);

    void deleteEmpleado(Integer id);
}
