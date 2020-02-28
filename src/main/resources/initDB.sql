create schema if not exists public;

create database go_it_homework with owner postgres;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

CREATE TABLE developers (
  id SERIAL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  age INT NOT NULL,
  email VARCHAR(50),
  sex VARCHAR(6),
  hire_date DATE,
  company_id INT,
  PRIMARY KEY (id),
  CHECK (age > 0)
);

alter table developers owner to postgres;

CREATE TABLE skills (
  id SERIAL,
  skill VARCHAR(20),
  level VARCHAR(20),
  PRIMARY KEY (id)
);

alter table skills owner to postgres;

CREATE TABLE developers_skills (
  id SERIAL,
  developer_id INT,
  skill_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (developer_id)
      REFERENCES developers (id),
  FOREIGN KEY (skill_id)
      REFERENCES skills (id)
);

alter table developers_skills owner to postgres;

CREATE TABLE projects (
  id SERIAL,
  name VARCHAR(50),
  release_date DATE,
  PRIMARY KEY (id)
);

alter table projects owner to postgres;

CREATE TABLE developer_projects (
  id SERIAL,
  developer_id INT,
  project_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (developer_id)
      REFERENCES developers (id),
  FOREIGN KEY (project_id)
      REFERENCES projects (id)
);

alter table developer_projects owner to postgres;

CREATE TABLE companies (
  id SERIAL,
  name VARCHAR(50),
  location VARCHAR(50),
  PRIMARY KEY (id)
);

alter table companies owner to postgres;

CREATE TABLE company_project (
  id SERIAL,
  company_id INT,
  project_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (company_id)
      REFERENCES companies (id),
  FOREIGN KEY (project_id)
      REFERENCES projects (id)
);

alter table company_project owner to postgres;

CREATE TABLE customers (
  id SERIAL,
  name VARCHAR(50),
  budget INT,
  PRIMARY KEY (id)
);

alter table customers owner to postgres;

CREATE TABLE customer_project (
  id SERIAL,
  customer_id INT,
  project_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id)
      REFERENCES customers (id),
  FOREIGN KEY (project_id)
      REFERENCES projects (id)
);

alter table customer_project owner to postgres;

--ALTER projects due to tasks
ALTER TABLE projects add column project_start DATE;

