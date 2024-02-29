package backend.jobportal.security;

import backend.jobportal.entity.Employee;
import backend.jobportal.entity.EmployeeRole;
import backend.jobportal.entity.Employer;
import backend.jobportal.entity.EmployerRole;
import backend.jobportal.exception.JobPortalException;
import backend.jobportal.repository.EmployeeRepository;
import backend.jobportal.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPortalUserDetails implements UserDetailsService {


    private EmployeeRepository employeeRepository;
    private EmployerRepository employerRepository;

    @Autowired
    public JobPortalUserDetails(EmployeeRepository employeeRepository, EmployerRepository employerRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Employee> employeeOptional = employeeRepository.findByEmail(username);

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            for (EmployeeRole role: employee.getRoles()) {


                authorities.add(new SimpleGrantedAuthority(role.getName()));

            }

            return new org.springframework.security.core.userdetails
                    .User(employee.getEmail(), employee.getPassword(), authorities);
        }


        Optional<Employer> employerOptional = employerRepository.findByEmail(username);

        if (employerOptional.isPresent()) {

            Employer employer = employerOptional.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            for (EmployerRole role : employer.getRoles()) {


                authorities.add(new SimpleGrantedAuthority(role.getName()));

            }

            return new org.springframework.security.core.userdetails
                    .User(employer.getEmail(), employer.getPassword(), authorities);
        }

        throw new JobPortalException(HttpStatus.BAD_REQUEST, "Email Not Found");
    }


}
