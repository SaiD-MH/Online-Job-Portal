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
