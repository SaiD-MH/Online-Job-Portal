package backend.jobportal.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

    private JobResponse jobResponse;
    private int applicationId;
    private String status;
    private LocalDateTime appliedDate;

}
