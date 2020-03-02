package com.homework.goit.common;

import com.homework.goit.company.CompanyService;
import com.homework.goit.customer.CustomerService;
import com.homework.goit.developer.DeveloperService;
import com.homework.goit.project.ProjectService;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;
    }

    public void process(){
        view.write("Welcome to Project Management System!\n(Enter 'help' to list available commands!)");
        while (true){
            String actions = view.read();
            switch (actions){
                case "help":{
                    availableCommands();
                    break;
                }
                case "add_developer":{
//                    view.write("Add developer!");
//                    createDeveloper();
                    view.write("Temporarily unavailable");
                    break;
                }
                case "add_project":{
                    view.write("Add project!");
                    createProject();
                    break;
                }
                case "add_company":{
                    view.write("Add company!");
                    createCompany();
                    break;
                }
                case "add_customer":{
                    view.write("Add customer!");
                    createCustomer();
                    break;
                }

                case "update_developer":{
//                    view.write("Update developer!");
//                    updateDeveloper();
                    view.write("Temporarily unavailable");
                    break;
                }
                case "update_project":{
                    view.write("Update project!");
                    updateProject();
                    break;
                }
                case "update_company":{
                    view.write("Update company!");
                    updateCompany();
                    break;
                }
                case "update_customer":{
                    view.write("Update customer!");
                    updateCustomer();
                    break;
                }

                case "get_by_id_developer":{
                    view.write("Get developer by id!");
                    getByIdDeveloper();
                    break;
                }
                case "get_by_id_project":{
                    view.write("Get project by id!");
                    getByIdProject();
                    break;
                }
                case "get_by_id_company":{
                    view.write("Get company by id!");
                    getByIdCompany();
                    break;
                }
                case "get_by_id_customer":{
                    view.write("Get customer by id!");
                    getByIdCustomer();
                    break;
                }

                case "get_all_developer":{
//                    view.write("Get all developer!");
//                    getAllDeveloper();
                    view.write("Temporarily unavailable");
                    break;
                }
                case "get_all_project":{
                    view.write("Get all project!");
                    getAllProject();
                    break;
                }
                case "get_all_company":{
                    view.write("Get all company!");
                    getAllCompany();
                    break;
                }
                case "get_all_customer":{
                    view.write("Get all customer!");
                    getAllCustomer();
                    break;
                }

                case "delete_developer":{
                    view.write("Delete developer!");
                    deleteDeveloper();
                    break;
                }
                case "delete_project":{
                    view.write("Delete project!");
                    deleteProject();
                    break;
                }
                case "delete_company":{
                    view.write("Delete company!");
                    deleteCompany();
                    break;
                }
                case "delete_customer":{
                    view.write("Delete customer!");
                    deleteCustomer();
                    break;
                }

                case "total_salary_in_project":{
                    view.write("Total salary in all projects!");
                    showSalaryProject();
                    break;
                }
                case "developers_in_project":{
                    view.write("Show developers in project!");
                    showProjectDevelopers();
                    break;
                }
                case "show_java_developers":{
                    view.write("List of Java developers!");
                    showJavaDevelopers();
                    break;
                }
                case "show_middle_developers":{
                    view.write("All middle developers!");
                    showMiddleDevelopers();
                    break;
                }
                case "show_projects":{
                    view.write("Current projects!");
                    showProjects();
                    break;
                }
                default:{
                    view.write("No command found!");
                    break;
                }
            }
        }
    }

    private void deleteCustomer() {
        CustomerService service = new CustomerService(view);
        service.delete();
    }

    private void deleteCompany() {
        CompanyService service = new CompanyService(view);
        service.delete();
    }

    private void deleteProject() {
        ProjectService service = new ProjectService(view);
        service.delete();
    }

    private void deleteDeveloper() {
        DeveloperService service = new DeveloperService(view);
        service.delete();
    }

    private void getAllCustomer() {
        CustomerService service = new CustomerService(view);
        service.getAll();
    }

    private void getAllCompany() {
        CompanyService service = new CompanyService(view);
        service.getAll();
    }

    private void getAllProject() {
        ProjectService service = new ProjectService(view);
        service.getAll();
    }

    private void getAllDeveloper() {
        DeveloperService service = new DeveloperService(view);
        service.getAll();
    }

    private void getByIdCustomer() {
        CustomerService service = new CustomerService(view);
        service.getById();
    }

    private void getByIdCompany() {
        CompanyService service = new CompanyService(view);
        service.getById();
    }

    private void getByIdProject() {
        ProjectService service = new ProjectService(view);
        service.getById();
    }

    private void getByIdDeveloper() {
        DeveloperService service = new DeveloperService(view);
        service.getById();
    }

    private void updateCustomer() {
        CustomerService service = new CustomerService(view);
        service.update();
    }

    private void updateCompany() {
        CompanyService service = new CompanyService(view);
        service.update();
    }

    private void updateProject() {
        ProjectService service = new ProjectService(view);
        service.update();
    }

    private void updateDeveloper() {
        DeveloperService service = new DeveloperService(view);
        service.update();
    }

    private void showProjects() {
        ProjectService service = new ProjectService(view);
        service.showProjects();
    }

    private void showProjectDevelopers() {
        DeveloperService service = new DeveloperService(view);
        service.showProjectDevelopers();
    }

    private void showSalaryProject() {
        ProjectService service = new ProjectService(view);
        service.showSalaryProject();
    }

    private void createProject() {
        ProjectService project = new ProjectService(view);
        project.createProject();
    }

    private void createCustomer() {
        CustomerService customer = new CustomerService(view);
        customer.createCustomer();
    }

    private void showMiddleDevelopers() {
        DeveloperService service = new DeveloperService(view);
        service.showMiddleDevelopers();
    }

    private void createDeveloper() {
        DeveloperService service = new DeveloperService(view);
        service.createDeveloper();
    }

    private void showJavaDevelopers() {
        DeveloperService service =new DeveloperService(view);
        service.showJavaDevelopers();
    }

    private void createCompany() {
        CompanyService service = new CompanyService(view);
        service.createCompany();
    }

    private void availableCommands() {
        String allCommands = "Available commands:" +
                "\n\t--help" +
                "\n\t--add_developer" +
                "\n\t--add_project" +
                "\n\t--add_company" +
                "\n\t--add_customer" +

                "\n\t--update_developer" +
                "\n\t--update_project" +
                "\n\t--update_company" +
                "\n\t--update_customer" +

                "\n\t--get_by_id_developer" +
                "\n\t--get_by_id_project" +
                "\n\t--get_by_id_company" +
                "\n\t--get_by_id_customer" +

                "\n\t--get_all_developer" +
                "\n\t--get_all_project" +
                "\n\t--get_all_company" +
                "\n\t--get_all_customer" +

                "\n\t--delete_developer" +
                "\n\t--delete_project" +
                "\n\t--delete_company" +
                "\n\t--delete_customer" +

                "\n\t--total_salary_in_project" +
                "\n\t--developers_in_project" +
                "\n\t--show_java_developers" +
                "\n\t--show_middle_developers" +
                "\n\t--show_projects";
        view.write(allCommands);
    }
}
