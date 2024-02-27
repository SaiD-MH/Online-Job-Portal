package backend.jobportal.controller;


import backend.jobportal.payload.EmployerApplicationsJobsDto;
import backend.jobportal.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs/jobApplications")
public class JobApplicationController {


    private JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<EmployerApplicationsJobsDto>> getAllJobApplications() {

        return ResponseEntity.ok(jobApplicationService.getAllApplications());
    }

}
