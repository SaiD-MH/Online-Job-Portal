package backend.jobportal.repository;

import backend.jobportal.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {


    @Query("from JobApplication j where j.employee.id=:employeeId")
    List<JobApplication> findAllApplicationByEmployeeId(@Param("employeeId") int employeeId);


}
