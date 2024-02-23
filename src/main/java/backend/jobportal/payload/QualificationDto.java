package backend.jobportal.payload;

import backend.jobportal.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualificationDto {
    private int id;
    private String degree;
    private Date startDate;
    private Date endDate;

}
