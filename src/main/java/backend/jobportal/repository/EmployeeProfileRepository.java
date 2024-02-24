package backend.jobportal.repository;

import backend.jobportal.entity.EmployeeProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfileInfo , Integer> {
}
