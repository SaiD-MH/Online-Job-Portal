package backend.jobportal.service;

import backend.jobportal.entity.JobCategory;

import java.util.List;

public interface JobCategoryService {

    JobCategory createJobCategory(JobCategory jobCategory);
    List<JobCategory>getAllJobCategories();

    JobCategory updateJobCategory(int id , JobCategory jobCategory);
    String deleteJobCategoryById(int id);



}
