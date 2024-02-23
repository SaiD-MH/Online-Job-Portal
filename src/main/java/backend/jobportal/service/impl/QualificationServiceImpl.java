package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.Qualification;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.QualificationDto;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.repository.QualificationRepository;
import backend.jobportal.service.QualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements QualificationService {


    private QualificationRepository qualificationRepository;
    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Autowired
    public QualificationServiceImpl(QualificationRepository qualificationRepository,
                                    ModelMapper modelMapper,
                                    EmployeeRepository employeeRepository
    ) {
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public QualificationDto createQualification(QualificationDto qualificationDto, int employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        // Set
        Qualification qualification = mapToQualification(qualificationDto);
        qualification.setEmployee(employee);
        // insert
        Qualification savedQualification = qualificationRepository.save(qualification);
        return mapToQualificationDto(savedQualification);
    }

    private Qualification mapToQualification(QualificationDto qualificationDto) {

        return modelMapper.map(qualificationDto, Qualification.class);
    }

    private QualificationDto mapToQualificationDto(Qualification qualification) {
        return modelMapper.map(qualification, QualificationDto.class);
    }

}
