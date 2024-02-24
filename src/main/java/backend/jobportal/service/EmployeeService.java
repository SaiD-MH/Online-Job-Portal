package backend.jobportal.service;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Employer;
import backend.jobportal.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(int employeeId);


}
