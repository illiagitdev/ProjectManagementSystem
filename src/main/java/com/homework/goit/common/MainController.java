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
                case "add_developer":{
                    view.write("Adding developer!");
                    createDeveloper();
                    break;
                }
                case "add_project":{
                    view.write("Adding project!");
                    createProject();
                    break;
                }
                case "add_company":{
                    view.write("Adding company!");
                    createCompany();
                    break;
                }
                case "add_customer":{
                    view.write("Adding customer!");
                    createCustomer();
                    break;
                }
                default:{
                    view.write("No command found!");
                    break;
                }
            }
        }
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
        String allCommands = String.format("Available commands:\n\t%s\n\t%s\n\t%s" +
                        "\n\t%s\n\t%s\n\t%s" +
                        "\n\t%s\n\t%s\n\t%s" +
                        "\n\t%s",
                "help", "total_salary_in_project", "developers_in_project",
                "show_java_developers", "show_middle_developers", "show_projects",
                "add_developer", "add_project", "add_company",
                "add_customer");
        view.write(allCommands);
    }
}
