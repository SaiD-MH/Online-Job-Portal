package backend.jobportal.service;

import backend.jobportal.entity.Job;
import backend.jobportal.payload.JobDto;
import backend.jobportal.payload.JobResponse;

import java.io.IOException;
import java.util.List;

public interface JobService {


    String  createJob(JobDto jobDto , int employerId) throws IOException;

    List<JobResponse>  findByAllJobsByEmployerId(int id);

    List<JobResponse> getAllJobs();

    String deleteJob(int employerId , int jobId);


}
