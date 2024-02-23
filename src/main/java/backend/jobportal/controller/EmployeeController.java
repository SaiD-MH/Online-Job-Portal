package backend.jobportal.controller;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Employer;
import backend.jobportal.entity.Qualification;
import backend.jobportal.payload.EmployeeDto;
import backend.jobportal.payload.QualificationDto;
import backend.jobportal.service.EmployeeService;
import backend.jobportal.service.QualificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private QualificationService qualificationService;

    public EmployeeController(EmployeeService employeeService, QualificationService qualificationService) {
        this.employeeService = employeeService;
        this.qualificationService = qualificationService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);

    }

    @PostMapping("/{employeeId}/qualifications")
    public ResponseEntity<QualificationDto> addQualification(@RequestBody QualificationDto qualification
            , @PathVariable("employeeId") int employeeId
    ) {

        return new ResponseEntity<>(qualificationService.createQualification(qualification, employeeId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        List<EmployeeDto> employees = employeeService.getAllEmployees();


        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }


}
