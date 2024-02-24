package backend.jobportal.service.impl;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.EmployeeProfileInfo;
import backend.jobportal.exception.ResourceNotFoundException;
import backend.jobportal.payload.EmployeeProfileDto;
import backend.jobportal.payload.EmployeeProfileResponse;
import backend.jobportal.repository.EmployeeProfileRepository;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.service.EmployeeProfileService;

import static backend.jobportal.utils.AppConstant.*;

import backend.jobportal.utils.SaveToDisk;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {


    private EmployeeRepository employeeRepository;
    private EmployeeProfileRepository employeeProfileRepository;
    private ModelMapper modelMapper;

    public EmployeeProfileServiceImpl(EmployeeRepository employeeRepository,
                                      EmployeeProfileRepository employeeProfileRepository,
                                      ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeProfileResponse createEmployeeProfile(EmployeeProfileDto employeeProfileDto, int employeeId)
            throws IOException {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );


        MultipartFile profilePhoto = employeeProfileDto.getPhoto();
        MultipartFile resume = employeeProfileDto.getResume();

        Path profilePhotoFullPath = Paths.get(PATH_TO_SAVE_PROFILE_PHOTOS + profilePhoto.getOriginalFilename());
        Path resumeFullPath = Paths.get(PATH_TO_SAVE_RESUMES + resume.getOriginalFilename());

        SaveToDisk.saveFile(profilePhoto, profilePhotoFullPath);
        SaveToDisk.saveFile(resume, resumeFullPath);


        EmployeeProfileInfo employeeProfile = new EmployeeProfileInfo();

        employeeProfile.setEmployee(employee);
        employeeProfile.setBio(employeeProfileDto.getBio());
        employeeProfile.setGithub(employeeProfileDto.getGithub());
        employeeProfile.setWebsite(employeeProfileDto.getWebsite());
        employeeProfile.setLinkedin(employeeProfileDto.getLinkedin());
        employeeProfile.setPhotoPath(profilePhotoFullPath.toString());
        employeeProfile.setResumePath(resumeFullPath.toString());


        return mapTOResponse(employeeProfileRepository.save(employeeProfile));
    }


    private EmployeeProfileResponse mapTOResponse(EmployeeProfileInfo employeeProfileInfo) {

        return modelMapper.map(employeeProfileInfo, EmployeeProfileResponse.class);
    }


}
