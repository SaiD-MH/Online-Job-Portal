package backend.jobportal.service;

import backend.jobportal.payload.JobApplicationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobApplicationService {

    String  applyJob(int employeeId, int jobId);
    List<JobApplicationDto> getAllApplicationsByEmployee(int employeeId);


    String cancelApplication(int employeeId, int jobId ,int applicationId);
}
