package backend.jobportal.service;

import backend.jobportal.entity.Qualification;
import backend.jobportal.payload.QualificationDto;

public interface QualificationService {

    QualificationDto createQualification(QualificationDto qualification , int employeeId);

}
