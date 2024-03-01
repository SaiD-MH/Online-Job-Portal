package backend.jobportal.controller;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Employer;
import backend.jobportal.entity.Qualification;
import backend.jobportal.payload.*;
import backend.jobportal.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "REST APIs for Employee Resource")
public class EmployeeController {

    private EmployeeService employeeService;
    private QualificationService qualificationService;
    private WorkExperienceService workExperienceService;
    private EmployeeProfileService employeeProfileService;
    private EmployeeSkillService employeeSkillService;
    private JobApplicationService jobApplicationService;


    public EmployeeController(EmployeeService employeeService, QualificationService qualificationService,
                              WorkExperienceService workExperienceService, EmployeeProfileService employeeProfileService,
                              EmployeeSkillService employeeSkillService, JobApplicationService jobApplicationService) {
        this.employeeService = employeeService;
        this.qualificationService = qualificationService;
        this.workExperienceService = workExperienceService;
        this.employeeProfileService = employeeProfileService;
        this.employeeSkillService = employeeSkillService;
        this.jobApplicationService = jobApplicationService;
    }


    // Employee Register Methods
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);

    }


    // Employee Profile Methods
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

    @PostMapping(value = "/{employeeId}/profileDetails", consumes = {"*/*"})
    public ResponseEntity<EmployeeProfileResponse>
    addProfileDetails(@ModelAttribute EmployeeProfileDto employeeProfileDto,
                      @PathVariable("employeeId") int employeeId
    ) throws IOException {

        return new ResponseEntity<>(employeeProfileService.createEmployeeProfile(employeeProfileDto, employeeId)
                , HttpStatus.CREATED);

    }

    @PostMapping(value = "/{employeeId}/skills", consumes = {"*/*"})
    public ResponseEntity<EmployeeSkillDto>
    addProfileDetails(@RequestBody EmployeeSkillDto employeeSkillDto,
                      @PathVariable("employeeId") int employeeId
    ) throws IOException {

        return new ResponseEntity<>(employeeSkillService.createSkill(employeeSkillDto, employeeId)
                , HttpStatus.CREATED);
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


    // Job Method Related to Employee Methods

    @PostMapping("/{employeeId}/jobs/{jobId}/apply")
    public ResponseEntity<String> applyForJob(@PathVariable("employeeId") int employeeId,
                                              @PathVariable("jobId") int jobId) {

        return new ResponseEntity<>(jobApplicationService.applyJob(employeeId, jobId), HttpStatus.CREATED);

    }

    @PostMapping("/{employeeId}/jobs/{jobId}/yourApplications/{applicationId}/cancel")
    public ResponseEntity<String> cancelJobApplication(@PathVariable("employeeId") int employeeId,
                                                       @PathVariable("jobId") int jobId,
                                                       @PathVariable("applicationId") int applicationId

    ) {

        return ResponseEntity.ok(jobApplicationService.cancelApplication(employeeId, jobId , applicationId));

    }


    @GetMapping("/{employeeId}/jobs/yourApplications")
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplicationByEmployee(
            @PathVariable("employeeId") int employeeId) {
        return ResponseEntity.ok(jobApplicationService.getAllApplicationsByEmployee(employeeId));
    }


}
