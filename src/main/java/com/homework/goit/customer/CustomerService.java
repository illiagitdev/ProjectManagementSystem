package com.homework.goit.customer;

import com.homework.goit.common.DataAccessObject;
import com.homework.goit.common.DatabaseConnector;
import com.homework.goit.common.View;

public class CustomerService {
    private View view;
    private DataAccessObject<Customer> customerDAO;

    public CustomerService(View view) {
        this.view = view;
        DatabaseConnector db = new DatabaseConnector();
        customerDAO = new CustomerDAO(db.getConnection());
    }

    public void createCustomer() {
        view.write("Enter customer name");
        String name = validate(view.read());
        view.write("Enter customer budget");
        int budget = validateNumber(view.read());
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBudget(budget);
        customerDAO.create(customer);
        view.write("Created.");
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
