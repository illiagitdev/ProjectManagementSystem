package com.homework.goit.company;

import com.homework.goit.common.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CompanyDAO extends DataAccessObject<Company> {
    private final String INSERT = "INSERT INTO companies(name, location) VALUES(?, ?);";
    private final String UPDATE = "UPDATE companies SET name = ?, location = ? WHERE id = ?;";
    private final String RETRIVE_BY_ID = "SELECT * FROM companies WHERE id = ?;";
    private final String RETRiVE_ALL = "SELECT * FROM companies;";
    private final String DELETE = "DELETE FROM companies WHERE id = ?;";

    public CompanyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Company company) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getLocation());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Company company) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getLocation());
            statement.setInt(3, company.getId());
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No company with id = " + company.getId());
            }
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());
        }
    }

    @Override
    public Company getById(int id) {
        Company company = null;
        try (PreparedStatement statement = connection.prepareStatement(RETRIVE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int newId = rs.getInt(1);
            company = new Company();
            company.setId(newId);
            company.setName(rs.getString(2));
            company.setLocation(rs.getString(3));
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());
        }
        return company;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(RETRiVE_ALL)) {
            ResultSet rs = statement.executeQuery();
            Company company;
            while (rs.next()) {
                company = new Company();
                company.setId(rs.getInt(1));
                company.setName(rs.getString(2));
                company.setLocation(rs.getString(3));
                companyList.add(company);
            }
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());
        }
        return companyList;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Company with id = " + id + " were not found!");
            }
        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());
        }
    }
}
