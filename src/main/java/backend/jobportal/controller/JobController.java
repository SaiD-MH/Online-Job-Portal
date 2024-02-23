package backend.jobportal.controller;

import backend.jobportal.entity.Job;
import backend.jobportal.entity.JobCategory;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.service.JobCategoryService;
import backend.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
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

    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @DeleteMapping({"/{employerId}/jobs/{jobId}"})
    public ResponseEntity<String> deleteJob(@PathVariable("employerId") int employerId,
                                            @PathVariable("jobId") int jobId) {

        return ResponseEntity.ok(jobService.deleteJob(employerId, jobId));

    }


}
