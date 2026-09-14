package fm.empleados.controller;

import fm.empleados.model.Empleado;
import fm.empleados.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String showAddEmployeeForm() {
        return "addEmployee"; // Return the name of the view (addEmployee.html)
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employeeForm") Empleado employeeForm) {
        log.info("addEmployee: {} ", employeeForm.toString());
        empleadoService.saveEmpleado(employeeForm);
        return "redirect:/"; // Redirect to the root URL after adding the employee
    }

    // Redirect to the edit employee page with the employee ID as a query parameter
    // Example URL: /editar?idEmpleado=1
    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String editEmployee(@RequestParam int idEmpleado, ModelMap model) {
        Empleado employee = empleadoService.getEmpleadoById(idEmpleado);
        log.info("editEmployee: {} ", employee);
        model.put("employee", employee); // Add the employee to the model
        return "editEmployee"; // Return the name of the view (editEmployee.html)
    }

    // Handle the form submission for editing an employee
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employeeForm") Empleado employeeForm) {
        log.info("editEmployee: {} ", employeeForm);
        empleadoService.saveEmpleado(employeeForm);
        return "redirect:/"; // Redirect to the root URL after editing the employee
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam int idEmpleado) {
        log.info("deleteEmployee: {} ", idEmpleado);
        empleadoService.deleteEmpleado(idEmpleado);
        return "redirect:/"; // Redirect to the root URL after deleting the employee
    }
}
