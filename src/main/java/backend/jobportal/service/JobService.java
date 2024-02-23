package backend.jobportal.service;

import backend.jobportal.entity.Job;
import backend.jobportal.payload.JobDto;

import java.io.IOException;
import java.util.List;

public interface JobService {

    List<Job> getAll();

    String  createJob(JobDto jobDto) throws IOException;

}
