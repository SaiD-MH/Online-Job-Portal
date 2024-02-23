package backend.jobportal.controller;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Employer;
import backend.jobportal.entity.Qualification;
import backend.jobportal.payload.EmployeeDto;
import backend.jobportal.payload.QualificationDto;
import backend.jobportal.payload.WorkExperienceDto;
import backend.jobportal.service.EmployeeService;
import backend.jobportal.service.QualificationService;
import backend.jobportal.service.WorkExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private QualificationService qualificationService;
    private WorkExperienceService workExperienceService;


    public EmployeeController(EmployeeService employeeService, QualificationService qualificationService,
                              WorkExperienceService workExperienceService) {
        this.employeeService = employeeService;
        this.qualificationService = qualificationService;
        this.workExperienceService = workExperienceService;
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

    @PostMapping("/{employeeId}/workExperiences")
    public ResponseEntity<WorkExperienceDto> addWorkExperience(@RequestBody WorkExperienceDto workExperience
            , @PathVariable("employeeId") int employeeId
    ) {

        return new ResponseEntity<>(workExperienceService.createWorkExperience(workExperience, employeeId), HttpStatus.CREATED);
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
