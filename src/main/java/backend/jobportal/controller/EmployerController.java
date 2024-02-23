package backend.jobportal.controller;

import backend.jobportal.entity.Employer;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.service.EmployerService;
import backend.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private EmployerService employerService;
    private JobService jobService;


    @Autowired
    public EmployerController(EmployerService employerService, JobService jobService) {
        this.employerService = employerService;
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {

        return new ResponseEntity<>(employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{employerId}/jobs", consumes = {"*/*"})
    public ResponseEntity<String> createJob(@ModelAttribute JobDto jobDto,
                                            @PathVariable("employerId") int employerId)
            throws IOException {

        return new ResponseEntity<>(jobService.createJob(jobDto, employerId), HttpStatus.CREATED);

    }


    @GetMapping(value = "/{employerId}/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobsByEmployer(@PathVariable("employerId") int employerId) {
        return ResponseEntity.ok(jobService.findByAllJobsByEmployerId(employerId));
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.findAllEmployers());
    }


    @DeleteMapping({"/{employerId}/jobs/{jobId}"})
    public ResponseEntity<String> deleteJob(@PathVariable("employerId") int employerId,
                                            @PathVariable("jobId") int jobId) {

        return ResponseEntity.ok(jobService.deleteJob(employerId, jobId));

    }

}
