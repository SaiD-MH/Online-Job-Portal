package backend.jobportal.controller;

import backend.jobportal.entity.Employer;
import backend.jobportal.payload.EmployerApplicationsJobsDto;
import backend.jobportal.payload.JobApplicationDto;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.repository.JobApplicationRepository;
import backend.jobportal.service.EmployerService;
import backend.jobportal.service.JobApplicationService;
import backend.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
@Tag(name = "REST APIs for Employer Resource")
public class EmployerController {

    private EmployerService employerService;
    private JobService jobService;
    private JobApplicationService jobApplicationService;


    @Autowired
    public EmployerController(EmployerService employerService, JobService jobService,
                              JobApplicationService jobApplicationService
    ) {
        this.employerService = employerService;
        this.jobService = jobService;
        this.jobApplicationService = jobApplicationService;
    }

    // Employers Related Functions
    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {

        return new ResponseEntity<>(employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.findAllEmployers());
    }


    // Employers Jobs Related Functions

    @PostMapping(value = "/{employerId}/jobs", consumes = {"*/*"})
    public ResponseEntity<String> createJob(@ModelAttribute JobDto jobDto,
                                            @PathVariable("employerId") int employerId)
            throws IOException {

        return new ResponseEntity<>(jobService.createJob(jobDto, employerId), HttpStatus.CREATED);

    }

    @PostMapping("/{employerId}/myApplications/{applicationId}")
    public ResponseEntity<String> updateJobApplicationsStatus(
            @PathVariable("applicationId") int applicationId,
            @RequestParam("status") String status) {

        return new ResponseEntity<>(jobApplicationService.updateApplicationStatusByEmployer(applicationId, status), HttpStatus.CREATED);

    }


    @GetMapping(value = "/{employerId}/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobsByEmployer(@PathVariable("employerId") int employerId) {
        return ResponseEntity.ok(jobService.findByAllJobsByEmployerId(employerId));
    }


    @GetMapping(value = "/{employerId}/myApplications")
    public ResponseEntity<List<EmployerApplicationsJobsDto>> getAllApplicationByEmployerId(
            @PathVariable("employerId") int employerId
    ) {
        return ResponseEntity.ok(jobApplicationService.getAllApplicationsByEmployer(employerId));
    }


    @DeleteMapping({"/{employerId}/jobs/{jobId}"})
    public ResponseEntity<String> deleteJob(@PathVariable("employerId") int employerId,
                                            @PathVariable("jobId") int jobId) {

        return ResponseEntity.ok(jobService.deleteJob(employerId, jobId));

    }

}
