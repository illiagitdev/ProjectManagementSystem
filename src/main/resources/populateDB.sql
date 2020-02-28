INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Mark', 'Greenhorn', 41, 'werGren@mail.com', 'male', '2012-12-4', 1); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Rosa', 'Green', 34, 'rose@mail.com', 'female', '2016-2-22', 1); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Kate', 'Tomson', 25, 'kate@mail.com', 'female', '2018-1-14', 3); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Rayan', 'McDan', 42, 'rayan@mail.com', 'male', '2009-5-30', 2); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Tom', 'Gray', 39, 'tom@mail.com', 'male', '2010-8-12', 1); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Kelly', 'Osborn', 28, 'kelly@mail.com', 'female', '2015-1-14', 2); 
INSERT INTO developers (first_name, last_name, age, email, sex, hire_date, company_id) VALUES ('Richard', 'Patrik', 47, 'richard@mail.com', 'male', '2006-3-4', 3); 

INSERT INTO skills (skill, level) VALUES ('Java', 'Junior');
INSERT INTO skills (skill, level) VALUES ('Java', 'Middle');
INSERT INTO skills (skill, level) VALUES ('Java', 'Senior');
INSERT INTO skills (skill, level) VALUES ('C++', 'Junior');
INSERT INTO skills (skill, level) VALUES ('C++', 'Middle');
INSERT INTO skills (skill, level) VALUES ('C++', 'Senior');
INSERT INTO skills (skill, level) VALUES ('C#', 'Junior');
INSERT INTO skills (skill, level) VALUES ('C#', 'Middle');
INSERT INTO skills (skill, level) VALUES ('C#', 'Senior');
INSERT INTO skills (skill, level) VALUES ('JS', 'Junior');
INSERT INTO skills (skill, level) VALUES ('JS', 'Middle');
INSERT INTO skills (skill, level) VALUES ('JS', 'Senior');

INSERT INTO developers_skills (developer_id, skill_id) VALUES (1, 1);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (1, 5);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (1, 11);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (2, 2);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (2, 4);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (2, 11);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (3, 1);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (3, 10);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (4, 9);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (4, 12);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (5, 5);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (5, 8);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (5, 12);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (6, 2);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (6, 4);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (6, 10);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (7, 3);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (7, 6);
INSERT INTO developers_skills (developer_id, skill_id) VALUES (7, 11);

INSERT INTO projects (name, release_date) VALUES ('Video Trecker', '2020-8-17');
INSERT INTO projects (name, release_date) VALUES ('Star Cloud Storage', '2022-1-11');
INSERT INTO projects (name, release_date) VALUES ('Banc Accountant', '2021-3-27');
INSERT INTO projects (name, release_date) VALUES ('Emterprise Billing Manager', '2021-9-5');
INSERT INTO projects (name, release_date) VALUES ('CMS for Prophy', '2020-11-7');

INSERT INTO developer_projects (developer_id, project_id) VALUES (1, 2);
INSERT INTO developer_projects (developer_id, project_id) VALUES (1, 4);
INSERT INTO developer_projects (developer_id, project_id) VALUES (2, 1);
INSERT INTO developer_projects (developer_id, project_id) VALUES (2, 5);
INSERT INTO developer_projects (developer_id, project_id) VALUES (3, 4);
INSERT INTO developer_projects (developer_id, project_id) VALUES (4, 3);
INSERT INTO developer_projects (developer_id, project_id) VALUES (5, 3);
INSERT INTO developer_projects (developer_id, project_id) VALUES (6, 1);
INSERT INTO developer_projects (developer_id, project_id) VALUES (7, 2);
INSERT INTO developer_projects (developer_id, project_id) VALUES (7, 4);

INSERT INTO companies (name, location) VALUES ('Goldby', 'Kyiv');
INSERT INTO companies (name, location) VALUES ('Rest', 'Vinnitsa');
INSERT INTO companies (name, location) VALUES ('Star', 'Lvov');

INSERT INTO company_project (company_id, project_id) VALUES (1, 2);
INSERT INTO company_project (company_id, project_id) VALUES (1, 4);
INSERT INTO company_project (company_id, project_id) VALUES (2, 1);
INSERT INTO company_project (company_id, project_id) VALUES (2, 5);
INSERT INTO company_project (company_id, project_id) VALUES (3, 3);

INSERT INTO customers (name, budget) VALUES ('Thomas', 450000);
INSERT INTO customers (name, budget) VALUES ('Merphy', 40000);
INSERT INTO customers (name, budget) VALUES ('Jonny', 280000);
INSERT INTO customers (name, budget) VALUES ('Albert', 340000);

INSERT INTO customer_project (customer_id, project_id) VALUES (1, 2);
INSERT INTO customer_project (customer_id, project_id) VALUES (1, 3);
INSERT INTO customer_project (customer_id, project_id) VALUES (2, 1);
INSERT INTO customer_project (customer_id, project_id) VALUES (3, 2);
INSERT INTO customer_project (customer_id, project_id) VALUES (3, 4);
INSERT INTO customer_project (customer_id, project_id) VALUES (4, 3);
INSERT INTO customer_project (customer_id, project_id) VALUES (4, 4);
