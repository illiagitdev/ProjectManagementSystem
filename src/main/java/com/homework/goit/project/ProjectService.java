package com.homework.goit.project;

import com.homework.goit.common.DataAccessObject;
import com.homework.goit.common.DatabaseConnector;
import com.homework.goit.common.View;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProjectService {
    private View view;
    private DataAccessObject<Project> projectDAO;
    private ProjectDAOExtended projectDAOExtended;

    public ProjectService(View view) {
        this.view = view;
        DatabaseConnector db = new DatabaseConnector();
        projectDAO = new ProjectDAO(db.getConnection());
        projectDAOExtended = (ProjectDAOExtended) projectDAO;
    }

    public void createProject() {
        view.write("Enter project name");
        String name = validate(view.read());
        view.write("Enter release date (format YYYY-MM-DD)");
        Date releaseDate = validateDate(view.read());
        view.write("Enter project cost");
        int cost = validateNumber(view.read());
        view.write("Enter project start date (format YYYY-MM-DD)");
        Date startDate = validateDate(view.read());
        Project project = new Project();
        project.setName(name);
        project.setReleaseDate(releaseDate);
        project.setCost(cost);
        project.setProjectStart(startDate);
        view.write("Creating project...");
        projectDAO.create(project);
    }

    public void update(){
        view.write("Enter project id");
        int id = validateNumber(view.read());
        view.write("Enter new project name");
        String name = validate(view.read());
        view.write("Enter new release date (format YYYY-MM-DD)");
        Date releaseDate = validateDate(view.read());
        view.write("Enter new project cost");
        int cost = validateNumber(view.read());
        view.write("Enter new project start date (format YYYY-MM-DD)");
        Date startDate = validateDate(view.read());
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setReleaseDate(releaseDate);
        project.setCost(cost);
        project.setProjectStart(startDate);
        view.write("Updating project...");
        projectDAO.update(project);
    }

    public void getById(){
        view.write("Enter project id");
        int id = validateNumber(view.read());
        view.write("Searching project...");
        Project project = projectDAO.getById(id);
        view.write(project.toString());
    }

    public void getAll(){
        view.write("Retrieving projects...");
        List<Project> projects = projectDAO.getAll();
        for (Project pr : projects) {
            view.write(pr.toString());
        }
    }

    public void delete(){
        view.write("Enter project id to delete");
        int id = validateNumber(view.read());
        view.write("Deleting project...");
        projectDAO.delete(id);
    }

    public void showSalaryProject() {
        view.write("Enter project id to see total salary");
        int id = validateNumber(view.read());
        view.write("Project id(" + id + "):");
        Map<String, Integer> projectSalary = projectDAOExtended.getSalaryByProject(id);
        view.write(projectSalary.toString());
    }

    public void showProjects() {
        view.write("Projects:");
        Map<Date, Map<String, Integer>> projects = projectDAOExtended.getProjects();
        Iterator<Map.Entry<Date, Map<String, Integer>>> iter = projects.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<Date, Map<String, Integer>> entry1 = iter.next();
            Map<String, Integer> entry2 = entry1.getValue();
            view.write(entry1.getKey() + ": " + entry2.toString());
        }
    }

    private Date validateDate(String read) {
        Date date = null;
        try {
            LocalDate hireDate = LocalDate.parse(read, DateTimeFormatter.ofPattern("YYYY-MM-DD"));
            date = Date.valueOf(hireDate);
        } catch (DateTimeParseException e) {
            view.write("Wrong date format!");
        }
        //todo: how to loop if exception?
        return date;
    }

    private int validateNumber(String value) {
        int result = 0;
        while  (value.trim().isEmpty()){
            view.write("Field can't be empty");
            value = view.read();
        }
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e){
            view.write("Not a number! Try again.");
        }
        return result;
    }

    private String validate(String value) {
        while (value.trim().isEmpty()){
            view.write("Field can't be empty");
            value = view.read();
        }
        return value;
    }
}
