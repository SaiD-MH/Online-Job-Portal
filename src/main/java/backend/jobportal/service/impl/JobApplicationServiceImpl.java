package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Job;
import backend.jobportal.entity.JobApplication;
import backend.jobportal.exception.JobPortalException;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.EmployerApplicationsJobsDto;
import backend.jobportal.payload.JobApplicationDto;
import backend.jobportal.payload.JobResponse;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.repository.JobApplicationRepository;
import backend.jobportal.repository.JobRepository;
import backend.jobportal.service.JobApplicationService;

import static backend.jobportal.utils.AppConstant.JOB_APPLICATION_STATUS.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private JobApplicationRepository jobApplicationRepository;
    private EmployeeRepository employeeRepository;
    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    @Autowired
    public JobApplicationServiceImpl(
            JobApplicationRepository jobApplicationRepository,
            EmployeeRepository employeeRepository,
            JobRepository jobRepository,
            ModelMapper modelMapper) {

        this.jobApplicationRepository = jobApplicationRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String applyJob(int employeeId, int jobId) {

        Employee employee = checkEmployeeExistence(employeeId);

        Job job = checkJobExistence(jobId);


        JobApplication jobApplication = new JobApplication();

        jobApplication.setEmployee(employee);
        jobApplication.setJob(job);
        jobApplication.setAppliedDate(LocalDateTime.now());
        jobApplication.setStatus(APPLIED.toString());
        jobApplicationRepository.save(jobApplication);
        return "Job Applied Successfully";
    }

    @Override
    public List<JobApplicationDto> getAllApplicationsByEmployee(int employeeId) {


        Employee employee = checkEmployeeExistence(employeeId);

        List<JobApplication> jobApplications = jobApplicationRepository.findAllApplicationByEmployeeId(employeeId);

        List<JobApplicationDto> AllJobApplications = jobApplications.stream().map((jobApp) -> {
                    JobApplicationDto jobApplicationDto = new JobApplicationDto();
                    // Fill the data
                    jobApplicationDto.setApplicationId(jobApp.getId());
                    jobApplicationDto.setStatus(jobApp.getStatus());
                    jobApplicationDto.setAppliedDate(jobApp.getAppliedDate());
                    jobApplicationDto.setJobResponse(mapToJobDto(jobApp.getJob()));
                    return jobApplicationDto;
                }
        ).collect(Collectors.toList());


        return AllJobApplications;
    }

    @Override
    public String cancelApplication(int employeeId, int jobId, int applicationId) {

        Employee employee = checkEmployeeExistence(employeeId);

        Job job = checkJobExistence(jobId);

        JobApplication jobApplication = checkJobApplicationExistence(applicationId);

        jobApplication.setStatus(CANCELED.toString());

        jobApplicationRepository.save(jobApplication);

        return "Job Application Cancel Successfully";

    }

    @Override
    public List<EmployerApplicationsJobsDto> getAllApplicationsByEmployer(int employerId) {

        return jobApplicationRepository.findAllApplicationForEmployer(employerId).stream()
                .map(
                        (job) -> {
                            job.setCompanyLogo(Paths.get(job.getCompanyLogo()).getFileName().toString());
                            return job;
                        }

                ).collect(Collectors.toList());
    }

    @Override
    public String updateApplicationStatusByEmployer(int applicationId, String status) {

        JobApplication jobApplication = checkJobApplicationExistence(applicationId);

        if (jobApplication.getStatus().equals(CANCELED.toString())) {
            throw new JobPortalException(HttpStatus.BAD_REQUEST,
                    "This Application is Already Canceled , You can't Change it");
        }

        jobApplication.setStatus(status);
        jobApplicationRepository.save(jobApplication);

        return "Application Status Changed Successfully";

    }

    @Override
    public  List<EmployerApplicationsJobsDto> getAllApplications() {

        return jobApplicationRepository.findAllApplication();

    }

    private JobResponse mapToJobDto(Job job) {

        JobResponse jobResponse = modelMapper.map(job, JobResponse.class);
        jobResponse.setCompanyLogo(Path.of(job.getLogoPath()).getFileName().toString());
        return jobResponse;
    }


    private Employee checkEmployeeExistence(int employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
        return employee;
    }

    private Job checkJobExistence(int jobId) {

        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new ResourceNotFoundException("Job", "id", jobId)
        );
        return job;
    }

    private JobApplication checkJobApplicationExistence(int applicationId) {

        JobApplication jobApplication = jobApplicationRepository.findById(applicationId).orElseThrow(
                () -> new ResourceNotFoundException("JobApplication", "id", applicationId)
        );
        return jobApplication;
    }

}
