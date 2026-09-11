package fm.empleados.controller;

import fm.empleados.model.Empleado;
import fm.empleados.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EmpleadoController {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private EmpleadoService empleadoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initialize(ModelMap model) {
        List<Empleado> employees = empleadoService.getEmpleados();
        employees.forEach(empleado -> log.info(empleado.toString()));
        // Se comparte el modelo con la vista (index.html)
        model.put("employees", employees);
        return "index"; // Return the name of the view (index.html)
    }



}
