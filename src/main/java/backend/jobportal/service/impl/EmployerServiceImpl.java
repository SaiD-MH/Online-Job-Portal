package backend.jobportal.service.impl;

import backend.jobportal.entity.Employer;
import backend.jobportal.repository.EmployerRepository;
import backend.jobportal.service.EmployerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;

    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public List<Employer> findAllEmployers() {

        return employerRepository.findAll();
    }

    @Override
    public Employer createEmployer(Employer employer) {

        return employerRepository.save(employer);
    }
}
