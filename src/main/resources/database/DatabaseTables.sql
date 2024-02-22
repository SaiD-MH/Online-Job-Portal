

create table employer (
id serial primary key ,
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
id serial primary key ,
title varchar(100) not null unique ,
description varchar(200) not null );




CREATE TABLE resumes (
    resume_id SERIAL PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(50),
    file_content BYTEA
);



create table job (
id serial primary key ,
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
jobId int references job(id));
