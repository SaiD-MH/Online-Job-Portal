package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }
}
