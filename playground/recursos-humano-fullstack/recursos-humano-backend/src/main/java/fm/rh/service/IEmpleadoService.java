package fm.rh.service;

import fm.rh.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> getAllEmpleados();

    public Empleado getEmpleadoById(int id);

    public Empleado saveEmpleado(Empleado empleado);

    public void deleteEmpleado(int id);

}
