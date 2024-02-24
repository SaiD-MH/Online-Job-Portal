package backend.jobportal.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerApplicationsJobsDto {

    private String companyName;
    private String CompanyLogo;
    private String jobTitle;
    private String jobCategory;
    private String type;
    private String employeeName;
    private String location;
    private int applicationId;
    private String status;
}
