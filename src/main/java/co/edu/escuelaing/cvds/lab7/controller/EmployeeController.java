package co.edu.escuelaing.cvds.lab7.controller;


import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;
    private static final String ACTION_1 = "redirect:/employees/list";

    @Autowired
    @GetMapping("/list")
    public String employees(Model model) {
        model.addAttribute("empleados",employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarEmpleado(Model model) {
        model.addAttribute("empleado", new Employee());
        return "createEmployee";
    }
    // Procesar el formulario y agregar el empleado
    @PostMapping("/agregar")
    public String agregarEmpleado(Employee empleado) {
        employeeService.addEmployee(empleado);
        return ACTION_1; // Redirigir a la lista de empleados
    }

    @GetMapping("/modificar/{employeeId}")
    public String mostrarFormularioModificarEmpleado(@PathVariable Long employeeId, Model model) {
        model.addAttribute("empleado",employeeService.getEmployee(employeeId));
        return "updateEmployee";
    }

    @PostMapping("/modificar/{employeeId}")
    public String ModificarEmpleado(@PathVariable Long employeeId,@ModelAttribute Employee updatedEmployee) {
        System.out.println(updatedEmployee);
        employeeService.updateEmployee(updatedEmployee);
        return ACTION_1;
    }

    @PostMapping("/eliminar/{employeeId}")
    public String eliminarEmployee(@PathVariable Long employeeId) {
        employeeService.deleteUser(employeeId);
        return ACTION_1;
    }

}
