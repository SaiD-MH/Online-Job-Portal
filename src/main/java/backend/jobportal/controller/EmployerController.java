package backend.jobportal.controller;

import backend.jobportal.entity.Employer;
import backend.jobportal.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {

        return new ResponseEntity<>(employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.findAllEmployers());
    }


}
