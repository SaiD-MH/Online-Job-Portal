package backend.jobportal.payload;

import backend.jobportal.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillDto {

    private int id;
    private String name;
    private int experience;
}
