package backend.jobportal.service.impl;

import backend.jobportal.entity.Employer;
import backend.jobportal.entity.Job;
import backend.jobportal.entity.JobCategory;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.repository.EmployerRepository;
import backend.jobportal.repository.JobCategoryRepository;
import backend.jobportal.repository.JobRepository;
import backend.jobportal.service.JobService;
import backend.jobportal.utils.AppConstant;
import backend.jobportal.utils.SaveToDisk;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;
    private EmployerRepository employerRepository;
    private JobCategoryRepository jobCategoryRepository;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper,
                          EmployerRepository employerRepository, JobCategoryRepository jobCategoryRepository) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.employerRepository = employerRepository;
        this.jobCategoryRepository = jobCategoryRepository;
    }

    @Override
    public String createJob(JobDto jobDto, int employerId) throws IOException {

        Employer employer = checkEmployerExist(employerId);
        JobCategory jobCategory = checkJobCategoryExist(jobDto.getJobCategory());


        MultipartFile companyLogo = jobDto.getCompanyLogo();

        Path fullPath = Paths.get(AppConstant.PathToSaveLogo + companyLogo.getOriginalFilename());

        SaveToDisk.saveFile(companyLogo, fullPath);

        Job job = mapToJob(jobDto);
        job.setLogoPath(fullPath.toString());
        job.setEmployer(employer);
        job.setJobCategory(jobCategory);

        Job savedJob = jobRepository.save(job);

        return "Job Created Successfully";
    }

    @Override
    public List<JobResponse> findByAllJobsByEmployerId(int id) {

        //     checkEmployerExist(employerId);


        return jobRepository.findByAllJobsByEmployerId(id).stream().map((job) -> {
            String fullPath = job.getCompanyLogo();

            Path path = Paths.get(fullPath);

            job.setCompanyLogo(path.getFileName().toString());
            return job;
        }).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getAllJobs() {

        return jobRepository.findByAllJobs();
    }

    @Override
    public String deleteJob(int employerId, int jobId) {

        checkEmployerExist(employerId);
        Job job = checkJobExist(jobId);
        job.setEmployer(null);
        job.setJobCategory(null);
        jobRepository.delete(job);
        return "Job Deleted Successfully";
    }

    @Override
    public JobResponse getJobByJobId(int jobId) {

        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new ResourceNotFoundException("Job", "id", jobId)
        );

        return mapToJobDto(job);
    }

    @Override
    public List<JobResponse> searchJob(String jobCategory, String jobType, String country) {


        return jobRepository.searchJobs(jobCategory, jobType, country);
    }


    private Employer checkEmployerExist(int employerId) {

        Employer employer = employerRepository.findById(employerId).orElseThrow(
                () -> new ResourceNotFoundException("Employer", "id", employerId)
        );
        return employer;
    }

    private JobCategory checkJobCategoryExist(String title) {
        JobCategory jobCategory = jobCategoryRepository.findByTitle(title).orElseThrow(
                () -> new ResourceNotFoundException("Job-Category", "title", -1)
        );

        return jobCategory;
    }


    private Job checkJobExist(int jobId) {

        return jobRepository.findById(jobId).orElseThrow(
                () -> new ResourceNotFoundException("Jobs", "id", jobId)
        );

    }


    private Job mapToJob(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        return job;
    }

    private JobResponse mapToJobDto(Job job) {

        JobResponse jobResponse = modelMapper.map(job, JobResponse.class);
        jobResponse.setCompanyLogo(Path.of(job.getLogoPath()).getFileName().toString());
        return jobResponse;
    }


}
