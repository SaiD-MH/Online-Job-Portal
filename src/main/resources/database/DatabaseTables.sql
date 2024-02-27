
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
id  serial primary key ,
title varchar(50) not null ,
company_name varchar(50) not null ,
job_description varchar(500) not null ,
skills varchar(500) not null ,
job_type varchar(100) not null ,
salary_range varchar(100) not null ,
experience varchar(100) not null ,
street varchar(100) not null ,
city varchar(100) not null ,
pin_code varchar(100) not null ,
country varchar(100) not null ,
logo_path varchar(100) not null ,
jobCategory_Id int references job_category(id) not null,
employer_id int references employer(id) not null
);


create table employee(
id serial primary key ,
first_name varchar(100) not null ,
last_name varchar(100) not null ,
email varchar(100) unique not null ,
password varchar(100) not null ,
contact_num varchar(50) not null ,
country varchar(100) not null ,
city varchar(50) not null ,
pin_code; varchar(20) not null ,
state varchar(100) not null ,
street varchar(100) not null
);


CREATE TABLE employee_profile (
    id  serial PRIMARY KEY,
    photo_path varchar(250) not null ,
    github varchar(100) not null ,
    linkedin varchar(100) not null ,
    bio varchar(500) not null ,
    website varchar(100) not null,
    resume_path varchar(200) not null,
    employee_id int references employee(id)
);



CREATE TABLE skill (
    id serial PRIMARY KEY,
    name varchar(100) not null ,
    experience int not null,
    employee_id int references employee(id)

);



CREATE TABLE qualification (
    id serial PRIMARY KEY,
    degree varchar(100) not null ,
    start_date date not null ,
    end_date date not null,
    employee_id int references employee(id)


);


CREATE TABLE work_experience(
    id serial PRIMARY KEY,
    company varchar(100) not null ,
    position varchar(100) not null ,
    start_date date not null ,
    end_date date not null
);


CREATE TABLE job_application (

    id serial PRIMARY KEY,
    status varchar(20) not null ,
    applied_date date not null ,
    employee_id int references employee(id),
    job_id int references job(id)

);

select * from job
join job_application on job.id = job_application.job_id
where job.employer_id=2;

SELECT *
FROM job
JOIN job_application ON job.id = job_application.job_id
WHERE job.employer_id = 3;


create table role (
id serial primary key ,
name varchar(50) unique not null
);

create table employee_roles(
employee_id int references employee(id),
role_id int references role(id)
);

create table employer_roles(
employer_id int references employer(id),
role_id int references role(id)
);