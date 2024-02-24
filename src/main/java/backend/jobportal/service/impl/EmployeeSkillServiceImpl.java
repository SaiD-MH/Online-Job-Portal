package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.EmployeeSkill;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.EmployeeSkillDto;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.repository.EmployeeSkillRepository;
import backend.jobportal.service.EmployeeSkillService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private EmployeeSkillRepository employeeSkillRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    ModelMapper modelMapper) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EmployeeSkillDto createSkill(EmployeeSkillDto employeeSkillDto, int employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        EmployeeSkill employeeSkill = mapToEntity(employeeSkillDto);
        employeeSkill.setEmployee(employee);
        EmployeeSkill db = employeeSkillRepository.save(employeeSkill);
        return mapToDto(db);
    }


    private EmployeeSkillDto mapToDto(EmployeeSkill employeeSkill) {

        return modelMapper.map(employeeSkill, EmployeeSkillDto.class);
    }

    private EmployeeSkill mapToEntity(EmployeeSkillDto employeeSkillDto) {

        return modelMapper.map(employeeSkillDto, EmployeeSkill.class);
    }


}
