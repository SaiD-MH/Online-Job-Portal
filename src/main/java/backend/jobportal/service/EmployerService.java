package backend.jobportal.service;

import backend.jobportal.entity.Employer;

import java.util.List;

public interface EmployerService {



    List<Employer> findAllEmployers();
    Employer createEmployer(Employer employer);
}
