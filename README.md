# Online Job Portal


The Online Job Portal using Spring Boot simple and lightweight job portal application built using Java and Spring Boot. It provides a platform for job seekers to browse and apply for available job listings, while recruiters can post job openings and manage applications



## Overview

The Online Job Portal ensures efficient job management for employers and job seekers. Employers can post job listings with detailed information, and employees can apply for jobs based on their qualifications and preferences. The system facilitates communication and updates between employers, employees, and administrators, streamlining the hiring process.

## Technologies Used

- Java 8
- Spring Boot 
- JWT for user authentication
- PostgreSQL 
- Maven 
- Postman 
- Hibernate
- Swagger (API's Documentation)

## Software Used

- IntelliJ IDEA backend development
- psql terminal-based front-end to PostgreSQL 
- Postman for API's Testing
  
## User Modules

1. **Administrator Module**
   - Login to the system
   - Register other administrators
   - Manage job categories
   - View all jobs and applicants
   - View and manage employers and employees

2. **Employer Module**
   - Register and login to the system
   - Post job listings with detailed information
   - View applicants for posted jobs
   - Update job status for applicants

3. **Employee Module**
   - Register and login to the system
   - Search and view job listings
   - Apply for jobs based on qualifications
   - View and update profile information
   - View applied jobs and status
   - Cancel applied jobs
## DB_SCHEMA
  ![DB_SCHEMA](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/database/DATABASE%20SCHEMA.png)

## API's Endpoints
  ### REST APIs for Auth
  ![REST APIs for Auth](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Auth.PNG)
  
       - POST `/api/auth/login`   - Login and obtain JWT authentication token.
       - POST `/api/auth/signin`  - Register a new user (Employer or Employee).
  
  ### REST APIs for Employer
  ![REST APIs for Employer](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Employer.png)  
      
      - POST `/api/employers` Create new Employer
      - POST `/api/employers/{employerId}/jobs` Create new Job
      - POST `/api/employers/{employerId}/myApplications/{applicationId}` Update Application Status
      - GET `/api/employers` List All Employers
      - GET `/api/employers/{employerId}/jobs` Get All Jobs Posted By Give employer ID
      - GET `/api/employers/{employerId}/myApplications` Get All Applications for Jobs Posted by Employer Id
      - DELETE `/api/employers/{employerId}/jobs/{jobId}` Delete Job by Posted By Employer
    
  ### REST APIs for Employee
    
  ![REST APIs for Employee](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Employer.png)
        
      - POST `/api/employees` Create new Employee
      - POST `/api/employees/{employeeId}/profileDetails` Edit Employee Pofile Details
      - POST `/api/employees/{employeeId}/skills` Edit Employee Skills
      - POST `/api/employees/{employeeId}/qualifications` Edit Employee Qualifications
      - POST `/api/employees/{employeeId}/workExperiences` Edit Employee Work Experience
      - POST `/api/employees/{employeeId}/jobs/{jobId}/apply` Employee Apply for job
      - GET `/api/employees` List All Employees
      - GET `/api/employees/{employeeId}` Get Employee By Id
      - GET `/api/employees/{employeeId}/jobs/yourApplications` Get Details  of application submitted by employee  
  
  ### REST APIs for Job Categories
  ![REST APIs for Job Categories](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Job%20Categories.PNG)
  
       - POST   `/api/jobCategories`      Create New Job Category
       - GET    `/api/jobCategories`      List All Job Categories
       - PUT    `/api/jobCategories/{id}` Update Existing Job Category
       - DELETE `/api/jobCategories/{id}` Delete Existing Job Category

   ### REST APIs for Job
  ![REST APIs for Job ](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Job.PNG)
  
      - Get `/api/jobs`         List All Jobs
      - Get `/api/jobs/{jobId}` Get Job By Id
      - Get `/api/jobs/search`  Search about Job by jobCategory & Location & Type (Part , Full) time

   ### REST APIs for Job Application
![REST APIs for Job Application ](https://raw.githubusercontent.com/SaiD-MH/Online-Job-Portal/main/src/main/resources/static/REST%20APIs%20for%20Job%20Application.PNG)
  
      - GET `/api/jobs/jobApplications` List All Applications
      
      
## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/SaiD-MH/Online-Job-Portal.git

2. Open the backend project in your preferred IDE (e.g., Spring Tool Suite, IntelliJ IDEA)

3. Configure database settings:
    - Open src/main/resources/application.properties.
    - Update the database URL, username, password, and other configurations as per your local setup.

4. Run the application:
    - Build and run the Spring Boot application.
    - The application will be deployed locally at http://localhost:8080.
  

