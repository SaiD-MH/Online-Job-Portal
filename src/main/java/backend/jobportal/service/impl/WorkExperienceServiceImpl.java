package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.WorkExperience;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.WorkExperienceDto;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.repository.WorkExperienceRepository;
import backend.jobportal.service.WorkExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private EmployeeRepository employeeRepository;
    private WorkExperienceRepository workExperienceRepository;
    private ModelMapper modelMapper;

    public WorkExperienceServiceImpl(EmployeeRepository employeeRepository,
                                     WorkExperienceRepository workExperienceRepository,
                                     ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkExperienceDto createWorkExperience(WorkExperienceDto workExperienceDto, int employeeId) {


        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        WorkExperience workExperience = mapToEntity(workExperienceDto);
        workExperience.setEmployee(employee);


        WorkExperience savedWorkExperience = workExperienceRepository.save(workExperience);

        return mapToDto(savedWorkExperience);

    }


    private WorkExperience mapToEntity(WorkExperienceDto workExperienceDto) {

        return modelMapper.map(workExperienceDto, WorkExperience.class);
    }

    private WorkExperienceDto mapToDto(WorkExperience workExperience) {
        return modelMapper.map(workExperience, WorkExperienceDto.class);
    }


}
