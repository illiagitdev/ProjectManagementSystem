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

    public void showJavaDevelopers() {
        view.write("Java developers:");
        List<Developer> developers = developerDAOExtended.getJavaDeveloper();
        for (Developer d: developers) {
            view.write(d.toString());
        }
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
        view.write("Enter developer sex");
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
        developerDAO.create(developer);
        view.write("developer created!");
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
