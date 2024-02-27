package backend.jobportal.repository;

import backend.jobportal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Optional<Employer> findByEmail(String email);

}
