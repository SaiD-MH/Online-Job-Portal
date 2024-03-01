package backend.jobportal.controller;

import backend.jobportal.entity.Job;
import backend.jobportal.entity.JobCategory;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.service.JobCategoryService;
import backend.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@Tag(name = "REST APIs for Job Resource")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponse> getJobByJobId(@PathVariable("jobId") int jobId) {

        return ResponseEntity.ok(jobService.getJobByJobId(jobId));
    }


    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }


    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam(value = "jobCategory", required = false, defaultValue = "0000000") String jobCategory,
                                                        @RequestParam(value = "jobType", required = false, defaultValue = "000000000") String jobType,
                                                        @RequestParam(value = "country", required = false, defaultValue = "000000000") String country


    ) {

        return ResponseEntity.ok(jobService.searchJob(jobCategory, jobType,country));

    }


}
