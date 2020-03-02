package com.homework.goit.customer;

import com.homework.goit.common.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {
    private final String INSERT = "INSERT INTO customers(name, budget) VALUES(?, ?);";
    private final String UPDATE = "UPDATE customers SET name = ?, budget = ? WHERE id = ?;";
    private final String RETRIVE_BY_ID = "SELECT * FROM customers WHERE id = ?;";
    private final String RETRiVE_ALL = "SELECT * FROM customers;";
    private final String DELETE = "DELETE FROM customers WHERE id = ?;";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getBudget());
            statement.execute();
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getBudget());
            statement.setInt(3, customer.getId());
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("No customer with id = " + customer.getId());
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
        }
    }

    @Override
    public Customer getById(int id) {
        Customer customer = null;
        try (PreparedStatement statement = connection.prepareStatement(RETRIVE_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int newId = rs.getInt(1);
            customer = new Customer();
            customer.setId(newId);
            customer.setName(rs.getString(2));
            customer.setBudget(rs.getInt(3));
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(RETRiVE_ALL)){
            ResultSet rs = statement.executeQuery();
            Customer customer;
            while (rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setBudget(rs.getInt(3));
                customerList.add(customer);
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
        }
        return customerList;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("Customer with id = " + id + " not found!");
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
        }
    }
}
