package backend.jobportal.controller;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Employer;
import backend.jobportal.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);

    }

    @GetMapping
    public Employee getEmployee(){
        return new Employee();
    }

}
