package backend.jobportal.service;

import backend.jobportal.payload.WorkExperienceDto;

public interface WorkExperienceService {


    WorkExperienceDto createWorkExperience(WorkExperienceDto workExperience, int employeeId);
}
