package fm.rh.controller;

import fm.rh.exception.ResourceNotFoundException;
import fm.rh.model.Empleado;
import fm.rh.service.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rh-app") // http://localhost:8080/rh-app
@CrossOrigin("http://localhost:3000") // React app - port - 3000
public class EmpleadoController {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados") // http://localhost:8080/rh-app/empleados
    public List<Empleado> getEmpleados() {
        List<Empleado> employees = this.empleadoService.getAllEmpleados();
        employees.forEach(employee -> log.info("getEmpleados {}", employee));
        return employees;
    }

    @GetMapping("/empleados/{id}") // http://localhost:8080/rh-app/empleados/1
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Integer id) {
        log.info("getEmpleado por id {}...", id);
        Empleado employee = this.empleadoService.getEmpleadoById(id);
        if (employee == null)
            throw new ResourceNotFoundException("Empleado no encontrado con id " + id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/empleados") // http://localhost:8080/rh-app/empleados
    public ResponseEntity<Empleado> saveEmpleado(@RequestBody Empleado employee) {
        log.info("saveEmpleado {}", employee);
        return ResponseEntity.ok(this.empleadoService.saveEmpleado(employee));
    }

    @PutMapping("/empleados/{id}") // http://localhost:8080/rh-app/empleados/1
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado employeeRequest) {
        log.info("updateEmpleado por id {} - empleado {}", id, employeeRequest);
        Empleado employee = this.empleadoService.getEmpleadoById(id);
        if (employee == null)
            throw new ResourceNotFoundException("Empleado no encontrado con id " + id);

        employee.setNombre(employeeRequest.getNombre());
        employee.setDepartamento(employeeRequest.getDepartamento());
        employee.setSueldo(employeeRequest.getSueldo());
        return ResponseEntity.ok(this.empleadoService.saveEmpleado(employee));
    }

    @DeleteMapping("/empleados/{id}") // http://localhost:8080/rh-app/empleados/1
    public ResponseEntity<Map<String, Boolean>> deleteEmpleado(@PathVariable Integer id) {
        log.info("deleteEmpleado por id {}...", id);
        Empleado employee = this.empleadoService.getEmpleadoById(id);
        if (employee == null)
            throw new ResourceNotFoundException("Empleado no encontrado con id " + id);

        this.empleadoService.deleteEmpleado(id);
        // Json response: {"deleted": true}
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
