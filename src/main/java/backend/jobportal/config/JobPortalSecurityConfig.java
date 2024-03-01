package backend.jobportal.config;


import backend.jobportal.filter.JwtAuthenticationFilter;
import backend.jobportal.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class JobPortalSecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    public JobPortalSecurityConfig(UserDetailsService userDetailsService,
                                   JwtAuthenticationEntryPoint authenticationEntryPoint,
                                   JwtAuthenticationFilter authenticationFilter
    ) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // don't create JSESSIONID
                .authorizeHttpRequests((request) -> request


                        // Security For Auth Controller
                        .requestMatchers("/api/auth/**").permitAll()

                        // Security For Job Category Controller
                        .requestMatchers(HttpMethod.POST, "/api/jobCategories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/jobCategories").hasAnyRole("ADMIN", "EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/api/jobCategories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/jobCategories/**").hasRole("ADMIN")

                        // Security For Job Employer Controller

                        // Employer itself
                        .requestMatchers(HttpMethod.POST, "/api/employers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employers").hasAnyRole("ADMIN")


                        // Employer & Job
                        .requestMatchers(HttpMethod.POST, "/api/employers/{employerId}/jobs").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/api/employers/{employerId}/myApplications/{applicationId}").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/api/employers/{employerId}/jobs").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employers/{employerId}/myApplications").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/employers/{employerId}/jobs/{jobId}").hasAnyRole("EMPLOYER")

                        // Security For Job Employee Controller

                        // ------------------------- Employee Profile Methods-------------

                        .requestMatchers(HttpMethod.POST, "/api/employees").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/qualifications").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/profileDetails").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/skills").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/{employeeId}").hasRole("EMPLOYEE")


                        // -------------------Job Method Related to Employee Methods---------

                        .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/jobs/{jobId}/apply").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/jobs/{jobId}/yourApplications/{applicationId}/cancel").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/{employeeId}/jobs/yourApplications").hasRole("EMPLOYEE")


                        // Security For Job Controller

                        .requestMatchers("/api/jobs/**").permitAll()

                        // Security For Job ApplicationController

                        .requestMatchers(HttpMethod.GET , "/api/jobs/jobApplications").hasRole("ADMIN")








                        // Security For API Documentation
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"


                        ).permitAll()
                        .anyRequest().authenticated()


                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
