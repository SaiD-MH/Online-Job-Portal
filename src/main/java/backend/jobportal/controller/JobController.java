package backend.jobportal.controller;

import backend.jobportal.entity.Job;
import backend.jobportal.entity.JobCategory;
import backend.jobportal.payload.JobDto;
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
@RequestMapping("/api/employers/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping(consumes = { "*/*" })
    public ResponseEntity<String> createJob(@ModelAttribute JobDto jobDto
                                           )
            throws IOException {

        return new ResponseEntity<>(jobService.createJob(jobDto), HttpStatus.CREATED);

    }


    @GetMapping
    public List<Job> getAll() {
        return jobService.getAll();
    }


}
