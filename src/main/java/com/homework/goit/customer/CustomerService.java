package com.homework.goit.customer;

import com.homework.goit.common.DataAccessObject;
import com.homework.goit.common.DatabaseConnector;
import com.homework.goit.common.View;

import java.util.List;

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
        view.write("Creating customer...");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBudget(budget);
        customerDAO.create(customer);
    }

    public void update(){
        view.write("Enter customer id for update");
        int id = validateNumber(view.read());
        view.write("Enter new customer name");
        String name = validate(view.read());
        view.write("Enter customer new budget");
        int budget = validateNumber(view.read());
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBudget(budget);
        view.write("Updating customer...");
        customerDAO.update(customer);
    }

    public Customer getById(){
        view.write("Enter customer id");
        int id = validateNumber(view.read());
        view.write("Searching customer...");
        return customerDAO.getById(id);
    }

    public List<Customer> getAll(){
        view.write("Retrieving customer...");
        return customerDAO.getAll();
    }

    public void delete(){
        view.write("Enter customer id to delete");
        int id = validateNumber(view.read());
        view.write("Deleting customer...");
        customerDAO.delete(id);
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
