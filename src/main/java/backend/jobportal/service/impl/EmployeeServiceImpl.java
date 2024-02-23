package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.EmployeeDto;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setRegisterDate(new Date());
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> dbEmployeesList = employeeRepository.findAll();

        return dbEmployeesList.stream().map((employee -> mapToDto(employee)))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {

        Employee employee = checkEmployeeIfExist(employeeId);

        return mapToDto(employee);
    }

    private EmployeeDto mapToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee checkEmployeeIfExist(int employeeId) {

        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
    }


}
