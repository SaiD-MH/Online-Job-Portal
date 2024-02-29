package backend.jobportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employer_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name ;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
