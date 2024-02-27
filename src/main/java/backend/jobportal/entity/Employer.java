package backend.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "employer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "contact_num")
    private String contactNumber;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private String pinCode;
    @Column(name = "state")
    private String state;
    @Column(name = "street")
    private String street;

    @JoinTable(
            name = "employer_roles",
            joinColumns = @JoinColumn(name = "employer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
