package backend.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "skill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="experience")
    private int experience;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
