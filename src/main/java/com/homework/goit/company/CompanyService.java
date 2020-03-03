package com.homework.goit.company;

import com.homework.goit.common.DataAccessObject;
import com.homework.goit.common.DatabaseConnector;
import com.homework.goit.common.View;

import java.util.List;

public class CompanyService {
    private View view;
    private DataAccessObject<Company> companyDAO;

    public CompanyService(View view) {
    this.view = view;
        DatabaseConnector db = new DatabaseConnector();
        companyDAO = new CompanyDAO(db.getConnection());
    }

    public void createCompany() {
        view.write("Enter company name");
        String name = validate(view.read());
        view.write("Enter company location");
        String location = view.read();
        Company company = new Company();
        company.setName(name);
        company.setLocation(location);
        view.write("Creating company...");
        companyDAO.create(company);
    }

    public void update(){
        view.write("Enter company id for update");
        int id = validateNumber(view.read());
        view.write("Enter new company name");
        String name = validate(view.read());
        view.write("Enter new company location");
        String location = view.read();
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setLocation(location);
        view.write("Updating company...");
        try {
            companyDAO.update(company);
        } catch (RuntimeException e){
            view.write(e.getMessage());
        }
    }

    public void getById(){
        view.write("Enter company id");
        int id = validateNumber(view.read());
        view.write("Searching company...");
        Company company = companyDAO.getById(id);
        if (company != null) {
            view.write(company.toString());
        } else {
            view.write("Company with such id were not found!");
        }
    }

    public void getAll(){
        view.write("Retrieving companies...");
        List<Company> companies = companyDAO.getAll();
        for (Company com: companies) {
            view.write(com.toString());
        }
    }

    public void delete(){
        view.write("Enter company id for deleting");
        int id = validateNumber(view.read());
        view.write("Deleting companies...");
        try {
            companyDAO.delete(id);
        } catch (RuntimeException e){
            view.write(e.getMessage());
        }
    }

    private int validateNumber(String value) {
        int result = 0;
        if  (!value.trim().isEmpty()) {
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                view.write("Not a number!");
            }
        }
        return result;
    }

    private String validate(String read) {
        while (read.trim().isEmpty()){
            view.write("Company name can't be empty");
            read = view.read();
        }
        return read;
    }
}
