
create table employer (
id int auto_increment primary key ,
first_name varchar(100) not null ,
last_name varchar(100) not null ,
email varchar(100) unique not null ,
password varchar(100) not null ,
country varchar(100) not null ,
pincode varchar(20) not null ,
city varchar(50) not null ,
contact_num varchar(50) not null ,
state varchar(100) not null ,
street varchar(100) not null

);


create table job_category(
id int auto_increment primary key ,
title varchar(100) not null unique ,
description varchar(200) not null );

create table job (
id int auto_increment primary key ,
title varchar(50) not null ,
companyName varchar(50) not null ,
jobDescription varchar(500) not null ,
skills varchar(500) not null ,
jobType varchar(100) not null ,
salaryRange varchar(100) not null ,
experience varchar(100) not null ,
street varchar(100) not null ,
city varchar(100) not null ,
pincode varchar(100) not null ,
country varchar(100) not null ,
logoPath varchar(100) not null ,
jobCategoryId int references job_category(id));


create table employee(
id int auto_increment primary key ,
first_name varchar(100) not null ,
last_name varchar(100) not null ,
email varchar(100) unique not null ,
password varchar(100) not null ,
contact_num varchar(50) not null ,
country varchar(100) not null ,
city varchar(50) not null ,
pincode varchar(20) not null ,
state varchar(100) not null ,
street varchar(100) not null
);

CREATE TABLE resume (
    id int auto_increment PRIMARY KEY,
    fileName VARCHAR(255) not null,
    fileType VARCHAR(50) not null,
    path  varchar(250) not null
);


CREATE TABLE employee_profile (
    id int auto_increment PRIMARY KEY,
    photoPath varchar(250) not null ,
    github varchar(100) not null ,
    linkedin varchar(100) not null ,
    bio varchar(500) not null ,
    website varchar(100) not null,
    resumeId int references resume(id),
    employeeId int references employee(id)
);



CREATE TABLE skill (
    id int auto_increment PRIMARY KEY,
    name varchar(100) not null ,
    experience int not null

);



CREATE TABLE employee_skills (
    id int auto_increment PRIMARY KEY,
    skillId int references skill(id) ,
    employeeId int references employee(id)
);



CREATE TABLE qualification (
    id int auto_increment PRIMARY KEY,
    degree varchar(100) not null ,
    startDate date not null ,
    endDate date not null
);




CREATE TABLE employee_qualifications (
    id int auto_increment PRIMARY KEY,
    employeeId int references employee(id) ,
    qualificationId int references qualification(id)
);



CREATE TABLE work_experience(
    id int auto_increment PRIMARY KEY,
    company varchar(100) not null ,
    postion varchar(100) not null ,
    startDate date not null ,
    endDate date not null
);


CREATE TABLE employee_experiences (
    id int auto_increment PRIMARY KEY,
    employeeId int references employee(id) ,
    experienceId int references work_experience(id)
);
