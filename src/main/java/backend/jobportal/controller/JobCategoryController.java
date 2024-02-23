package backend.jobportal.controller;

import backend.jobportal.entity.JobCategory;
import backend.jobportal.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/jobCategories")
public class JobCategoryController {

    private JobCategoryService jobCategoryService;

    @Autowired
    public JobCategoryController(JobCategoryService jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }


    @PostMapping
    public ResponseEntity<JobCategory> addJobCategory(@RequestBody JobCategory jobCategory) {

        return new ResponseEntity<>(jobCategoryService.createJobCategory(jobCategory), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<JobCategory>> getAllJobCategories() {

        return ResponseEntity.ok(jobCategoryService.getAllJobCategories());
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobCategory> updateJobCategory(@PathVariable("id") int id,
                                                         @RequestBody JobCategory jobCategory) {

        return ResponseEntity.ok(jobCategoryService.updateJobCategory(id, jobCategory));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobCategory(@PathVariable("id") int id) {

        return ResponseEntity.ok(jobCategoryService.deleteJobCategoryById(id));
    }

}
