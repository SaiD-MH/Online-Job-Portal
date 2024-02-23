package backend.jobportal.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private int jobId;
    private String title;
    private String companyName;
    private String jobDescription;
    private String skills;
    private String jobType;
    private String salaryRange;
    private String experience;
    private String street;
    private String city;
    private String PinCode;
    private String country;
    private String  companyLogo;
    private String jobCategory;
    private int employerId;

}
