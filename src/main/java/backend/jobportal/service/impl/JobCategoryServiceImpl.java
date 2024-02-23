package backend.jobportal.service.impl;

import backend.jobportal.entity.JobCategory;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.repository.JobCategoryRepository;
import backend.jobportal.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryServiceImpl implements JobCategoryService {


    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    public JobCategoryServiceImpl(JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    @Override
    public JobCategory createJobCategory(JobCategory jobCategory) {

        return jobCategoryRepository.save(jobCategory);

    }

    @Override
    public List<JobCategory> getAllJobCategories() {

        return jobCategoryRepository.findAll();

    }

    @Override
    public JobCategory updateJobCategory(int id, JobCategory jobCategory) {
        JobCategory dbJobCategory = isJobCategoryExist(id);

        dbJobCategory.setTitle(jobCategory.getTitle());
        dbJobCategory.setDescription(jobCategory.getDescription());

        return jobCategoryRepository.save(dbJobCategory);
    }

    @Override
    public String deleteJobCategoryById(int id) {


        JobCategory dbJobCategory = isJobCategoryExist(id);

        jobCategoryRepository.delete(dbJobCategory);

        return "Job Category Deleted Successfully";
    }


    private JobCategory isJobCategoryExist(int id) {
        JobCategory dbJobCategory = jobCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Job Category", "id", id)
        );

        return dbJobCategory;
    }


}
