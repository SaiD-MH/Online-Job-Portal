package backend.jobportal.service;

import backend.jobportal.payload.EmployeeProfileDto;
import backend.jobportal.payload.EmployeeProfileResponse;

import java.io.IOException;

public interface EmployeeProfileService {

    EmployeeProfileResponse createEmployeeProfile(EmployeeProfileDto employeeProfileDto , int employeeId) throws IOException;

}
