package fm.empleados.service;

import fm.empleados.model.Empleado;
import fm.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
