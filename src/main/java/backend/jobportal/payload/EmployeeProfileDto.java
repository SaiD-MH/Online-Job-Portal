package backend.jobportal.payload;

import backend.jobportal.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProfileDto {

    private int id;
    private MultipartFile photo;
    private String github;
    private String linkedin;
    private String bio;
    private String website;
    private MultipartFile resume;

}
