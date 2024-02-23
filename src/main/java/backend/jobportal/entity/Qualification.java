package backend.jobportal.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "qualification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "degree")
    private String degree;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
