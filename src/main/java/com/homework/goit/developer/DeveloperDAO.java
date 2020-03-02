package com.homework.goit.developer;

import com.homework.goit.common.DataAccessObject;

import java.sql.*;
import java.util.*;

public class DeveloperDAO extends DataAccessObject<Developer> implements DeveloperDAOExtended<Developer> {
    private final String INSERT = "INSERT INTO developers(first_name, last_name, age, email, sex, hire_date, company_id, " +
            "salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, age = ?, email = ?, sex = ?, " +
            "hire_date = ?, company_id = ?, salary = ? WHERE id = ?;";
    private final String RETRIVE_BY_ID = "SELECT * FROM developers WHERE id = ?;";
    private final String RETRiVE_ALL = "SELECT * FROM developers;";
    private final String DELETE = "DELETE FROM developers WHERE id = ?;";

    private final String GET_ALL_JAVA_DEVELOPERS = "SELECT dev.* FROM developers dev " +
            "JOIN developers_skills ds ON dev.id = ds.developer_id " +
            "JOIN skills s on ds.skill_id = s.id " +
            "WHERE s.skill = ?;";
    private final String GET_ALL_MIDDLE_DEVELOPERS = "SELECT s.skill, dev.* FROM developers dev " +
            "JOIN developers_skills ds ON dev.id = ds.developer_id " +
            "JOIN skills s on ds.skill_id = s.id " +
            "WHERE s.level = ? " +
            "GROUP BY dev.id, s.skill;";
    private final String DEVELOPERS_IN_PROJECT = "SELECT pr.name, dev.* FROM developers dev " +
            "JOIN  developer_projects dev_p ON dev.id = dev_p.developer_id " +
            "JOIN projects pr ON pr.id = dev_p.project_id " +
            "WHERE pr.id = ? " +
            "GROUP BY pr.name, dev.id " +
            "ORDER BY pr.name;";


    public DeveloperDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Developer developer) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getAge());
            statement.setString(4, developer.getEmail());
            statement.setString(5, developer.getSex());
            statement.setDate(6, developer.getHireDate());
            statement.setInt(7, developer.getCompanyId());
            statement.setInt(8, developer.getSalary());
            statement.execute();
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
    }

    @Override
    public void update(Developer developer) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getAge());
            statement.setString(4, developer.getEmail());
            statement.setString(5, developer.getSex());
            statement.setDate(6, developer.getHireDate());
            statement.setInt(7, developer.getCompanyId());
            statement.setInt(8, developer.getSalary());
            statement.setInt(9, developer.getId());
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("No developer with id = " + developer.getId());
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
    }

    @Override
    public Developer getById(int id) {
        Developer developer = null;
        try (PreparedStatement statement = connection.prepareStatement(RETRIVE_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            int length = 0;
            if(rs.last()){
                length = rs.getRow();
                rs.first();
            }
            if(length == 0){
                throw new RuntimeException("Developer not found! No such id!");
            }
            developer = new Developer();
            developer.setId(rs.getInt(1));
            developer.setFirstName(rs.getString(2));
            developer.setLastName(rs.getString(3));
            developer.setAge(rs.getInt(4));
            developer.setEmail(rs.getString(5));
            developer.setSex(rs.getString(6));
            developer.setHireDate(rs.getDate(7));
            developer.setCompanyId(rs.getInt(8));
            developer.setSalary(rs.getInt(9));
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(RETRiVE_ALL)){
            ResultSet rs = statement.executeQuery();
            Developer developer;
            while (rs.next()){
                developer = new Developer();
                developer.setId(rs.getInt(1));
                developer.setFirstName(rs.getString(2));
                developer.setLastName(rs.getString(3));
                developer.setAge(rs.getInt(4));
                developer.setEmail(rs.getString(5));
                developer.setSex(rs.getString(6));
                developer.setHireDate(rs.getDate(7));
                developer.setCompanyId(rs.getInt(8));
                developer.setSalary(rs.getInt(9));
                developers.add(developer);
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
        return developers;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("Developer with id = " + id + " not found!");
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
    }

    @Override
    public List<Developer> getJavaDeveloper() {
        List<Developer> developers = new LinkedList<>();;
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_JAVA_DEVELOPERS)){
            statement.setString(1, "Java");
            ResultSet rs = statement.executeQuery();
            Developer developer;
            while (rs.next()){
                developer = new Developer();
                developer.setId(rs.getInt(1));
                developer.setFirstName(rs.getString(2));
                developer.setLastName(rs.getString(3));
                developer.setAge(rs.getInt(4));
                developer.setEmail(rs.getString(5));
                developer.setSex(rs.getString(6));
                developer.setHireDate(rs.getDate(7));
                developer.setCompanyId(rs.getInt(8));
                developer.setSalary(rs.getInt(9));
                developers.add(developer);
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
        return developers;
    }

    @Override
    public Map<String, Developer> getMiddleDevelopers() {
        Map<String, Developer> developers = new LinkedHashMap<>();;
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_MIDDLE_DEVELOPERS)){
            statement.setString(1, "Middle");
            ResultSet rs = statement.executeQuery();
            Developer developer;
            String skill;
            int n = 1;
            while (rs.next()){
                developer = new Developer();
                skill = rs.getString(1);
                developer.setId(rs.getInt(2));
                developer.setFirstName(rs.getString(3));
                developer.setLastName(rs.getString(4));
                developer.setAge(rs.getInt(5));
                developer.setEmail(rs.getString(6));
                developer.setSex(rs.getString(7));
                developer.setHireDate(rs.getDate(8));
                developer.setCompanyId(rs.getInt(9));
                developer.setSalary(rs.getInt(10));
                developers.put(String.format("%s (%d) ", skill , n++), developer);
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
        return developers;
    }

    @Override
    public Map<String, Developer> getDevelopersInProject(int id) {
        Map<String, Developer> developers = new LinkedHashMap<>();;
        try (PreparedStatement statement = connection.prepareStatement(DEVELOPERS_IN_PROJECT)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Developer developer;
            String project;
            int n = 1;
            while (rs.next()){
                developer = new Developer();
                project = rs.getString(1);
                developer.setId(rs.getInt(2));
                developer.setFirstName(rs.getString(3));
                developer.setLastName(rs.getString(4));
                developer.setAge(rs.getInt(5));
                developer.setEmail(rs.getString(6));
                developer.setSex(rs.getString(7));
                developer.setHireDate(rs.getDate(8));
                developer.setCompanyId(rs.getInt(9));
                developer.setSalary(rs.getInt(10));
                developers.put(String.format("%s (%d) ", project , n++), developer);
            }
        } catch (SQLException e) {
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
            }
        }
        return  developers;
    }
}
