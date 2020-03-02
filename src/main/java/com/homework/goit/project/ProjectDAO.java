package com.homework.goit.project;

import com.homework.goit.common.DataAccessObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProjectDAO extends DataAccessObject<Project> implements ProjectDAOExtended {
    private final String INSERT = "INSERT INTO projects(name, release_date, cost, project_start) VALUES(?, ?, ?, ?);";
    private final String UPDATE = "UPDATE projects SET name = ?, release_date = ?, cost = ?, project_start = ? " +
            "WHERE id = ?;";
    private final String RETRIVE_BY_ID = "SELECT * FROM projects WHERE id = ?;";
    private final String RETRiVE_ALL = "SELECT * FROM projects;";
    private final String DELETE = "DELETE FROM projects WHERE id = ?;";

    private final String PROJECT_BY_SALARY ="SELECT pr.name, sum(dev.salary) FROM developers dev " +
            "JOIN  developer_projects dev_p ON dev.id = dev_p.developer_id " +
            "JOIN projects pr ON pr.id = dev_p.project_id " +
            "WHERE pr.id = ?  GROUP BY pr.name;";
    private final String GET_PROJECTS = "SELECT pr.project_start, pr.name, count(d.id) FROM projects pr " +
            "JOIN developer_projects dp on pr.id = dp.project_id " +
            "JOIN developers d on dp.developer_id = d.id " +
            "GROUP BY pr.name, pr.project_start;";

    public ProjectDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Project project) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, project.getName());
            statement.setDate(2, project.getReleaseDate());
            statement.setInt(3, project.getCost());
            statement.setDate(4, project.getProjectStart());
            statement.execute();
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
    }

    @Override
    public void update(Project project) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, project.getName());
            statement.setDate(2, project.getReleaseDate());
            statement.setInt(3, project.getCost());
            statement.setDate(4, project.getProjectStart());
            statement.setInt(5, project.getId());
            statement.execute();
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("No project with id = " + project.getId());
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
    }

    @Override
    public Project getById(int id) {
        Project project = null;
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
            project = new Project();
            project.setId(rs.getInt(1));
            project.setName(rs.getString(2));
            project.setReleaseDate(rs.getDate(3));
            project.setCost(rs.getInt(4));
            project.setProjectStart(rs.getDate(5));
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
        return project;
    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(RETRiVE_ALL)){
            ResultSet rs = statement.executeQuery();
            Project project;
            while (rs.next()){
                project = new Project();
                project.setId(rs.getInt(1));
                project.setName(rs.getString(2));
                project.setReleaseDate(rs.getDate(3));
                project.setCost(rs.getInt(4));
                project.setProjectStart(rs.getDate(5));
                projects.add(project);
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
        return projects;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new RuntimeException("Project with id = " + id + " not found!");
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
    }

    @Override
    public Map<String, Integer> getSalaryByProject(int id) {
        Map<String, Integer> result = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(PROJECT_BY_SALARY)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String key = rs.getString(1);
                int value = rs.getInt(2);
                result.put(key, value);
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
        return result;
    }

    @Override
    public Map<Date, Map<String, Integer>> getProjects() {
        Map<Date, Map<String, Integer>> result1 = new HashMap<>();
        Map<String, Integer> result2;
        try (PreparedStatement statement = connection.prepareStatement(GET_PROJECTS)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate(1);
                String name = rs.getString(2);
                int value = rs.getInt(3);
                result2 = new LinkedHashMap<>();
                result2.put(name, value);
                result1.put(date, result2);
            }
        } catch (SQLException e) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e.getNextException();
        }
        return result1;
    }
}
