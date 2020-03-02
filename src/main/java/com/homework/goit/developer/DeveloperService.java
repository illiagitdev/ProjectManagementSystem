package com.homework.goit.developer;

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

public class DeveloperService {
    private View view;
    private DataAccessObject<Developer> developerDAO;
    private DeveloperDAOExtended<Developer> developerDAOExtended;

    public DeveloperService(View view) {
        this.view = view;
        DatabaseConnector db = new DatabaseConnector();
        developerDAO = new DeveloperDAO(db.getConnection());
        developerDAOExtended = (DeveloperDAO) developerDAO;
    }

    public void createDeveloper() {
        view.write("Enter developer first name");
        String firstName = validate(view.read());
        view.write("Enter developer last name");
        String lastName = validate(view.read());
        view.write("Enter developer age");
        int age = validateNumber(view.read());
        view.write("Enter developer email");
        String email = validate(view.read());
        view.write("Enter developer sex(male, female)");
        String sex = validate(view.read());
        view.write("Enter developer hire date (format YYYY-MM-DD)");
        Date hireDate = validateDate(view.read());
        view.write("Enter developer company id");
        int companyId = validateNumber(view.read());
        view.write("Enter developer salary");
        int salary = validateNumber(view.read());
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAge(age);
        developer.setEmail(email);
        developer.setSex(sex);
        developer.setHireDate(hireDate);
        developer.setCompanyId(companyId);
        developer.setSalary(salary);
        view.write("Creating developer...");
        developerDAO.create(developer);
    }

    public void update(){
        view.write("Enter id developer for update");
        int id = validateNumber(view.read());
        view.write("Enter developer new first name");
        String firstName = validate(view.read());
        view.write("Enter developer new last name");
        String lastName = validate(view.read());
        view.write("Enter developer new age");
        int age = validateNumber(view.read());
        view.write("Enter developer new email");
        String email = validate(view.read());
        view.write("Enter developer sex(male, female)");
        String sex = validate(view.read());
        view.write("Enter developer hire date (format YYYY-MM-DD)");
        Date hireDate = validateDate(view.read());
        view.write("Enter developer new company id");
        int companyId = validateNumber(view.read());
        view.write("Enter developer new salary");
        int salary = validateNumber(view.read());
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAge(age);
        developer.setEmail(email);
        developer.setSex(sex);
        developer.setHireDate(hireDate);
        developer.setCompanyId(companyId);
        developer.setSalary(salary);
        view.write("Updating developer...");
        try {
            developerDAO.update(developer);
        } catch (RuntimeException e){
            view.write(e.getMessage());
        }
    }

    public void getById(){
        view.write("Enter id developer to search");
        int id = validateNumber(view.read());
        view.write("Searching for developer...");
        Developer developer = developerDAO.getById(id);
        if (developer != null) {
            view.write(developer.toString());
        } else {
            view.write("Developer with such id were not found!");
        }
    }

    public void getAll(){
        view.write("Retrieving developers...");
        List<Developer> developers = developerDAO.getAll();
        for (Developer dev : developers) {
            view.write(dev.toString());
        }
    }

    public void delete(){
        view.write("Enter id developer to delete");
        int id = validateNumber(view.read());
        view.write("Deleting developer...");
        try {
            developerDAO.delete(id);
        } catch (RuntimeException e){
            view.write(e.getMessage());
        }
    }

    public void showJavaDevelopers() {
        view.write("Java developers:");
        List<Developer> developers = developerDAOExtended.getJavaDeveloper();
        for (Developer d: developers) {
            view.write(d.toString());
        }
    }

    public void showMiddleDevelopers() {
        view.write("All Middle developers by skill.");
        Map<String, Developer> developers = developerDAOExtended.getMiddleDevelopers();
        Iterator<Map.Entry<String, Developer>> iter = developers.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String, Developer> entry = iter.next();
            view.write(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void showProjectDevelopers() {//todo: if -n, 0 or >size it exit without message
        view.write("Enter project id to see it's developers");
        int id =validateNumber(view.read());
        Map<String, Developer> projectDevelopers = developerDAOExtended.getDevelopersInProject(id);
        Iterator<Map.Entry<String, Developer>> iter = projectDevelopers.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String, Developer> entry = iter.next();
            view.write(entry.getKey() + ": " + entry.getValue());
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
            view.write("Not a number!");
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
