package backend.jobportal.service;

import backend.jobportal.entity.Employer;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface EmployerService {



    List<Employer> findAllEmployers();

    Employer createEmployer(Employer employer);
}
