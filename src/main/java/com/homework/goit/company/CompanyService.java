package com.homework.goit.company;

import com.homework.goit.common.DataAccessObject;
import com.homework.goit.common.DatabaseConnector;
import com.homework.goit.common.View;

public class CompanyService {
    private View view;
    DataAccessObject<Company> companyDAO;

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
        companyDAO.create(company);
        view.write("company created!");
    }

    private String validate(String read) {
        while (read.trim().isEmpty()){
            view.write("Company name can't be empty");
            read = view.read();
        }
        return read;
    }
}
