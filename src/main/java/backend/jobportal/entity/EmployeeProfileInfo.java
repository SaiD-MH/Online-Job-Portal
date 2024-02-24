package backend.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProfileInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "photo_path")
    private String photoPath;
    @Column(name = "github")
    private String github;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "bio")
    private String bio;
    @Column(name = "website")
    private String website;
    @Column(name = "resume_path")
    private String resumePath;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
