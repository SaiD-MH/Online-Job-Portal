package backend.jobportal.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {

    private int id;
    private String company;
    private String position;
    private Date startDate;
    private Date endDate;


}
