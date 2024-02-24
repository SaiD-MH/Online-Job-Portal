package backend.jobportal.service;

import backend.jobportal.payload.EmployeeSkillDto;

public interface EmployeeSkillService {

    EmployeeSkillDto createSkill(EmployeeSkillDto employeeSkillDto, int employeeId);

}
