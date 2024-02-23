package backend.jobportal.repository;

import backend.jobportal.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {

    Optional<JobCategory> findByTitle(String title);
}
