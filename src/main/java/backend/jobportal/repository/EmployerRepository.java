package backend.jobportal.repository;

import backend.jobportal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
}
