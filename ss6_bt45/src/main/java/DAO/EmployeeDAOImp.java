package DAO;


import model.Employee;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements bt4.EmployeeDAO {
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call findAllEmployee()}")) {
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("emp_id"));
                employee.setName(rs.getString("emp_name"));
                employee.setBirthday(rs.getDate("emp_dob").toLocalDate());
                employee.setPhone(rs.getString("emp_phone"));
                employee.setSalary(rs.getDouble("emp_salary"));
                employee.setPosition(rs.getString("emp_position"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all employees", e);
        }
        return employees;
    }

    @Override
    public boolean save(Employee employee) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call add_employee(?,?,?,?,?)}")) {
            callSt.setString(1, employee.getName());
            callSt.setDate(2, java.sql.Date.valueOf(employee.getBirthday()));
            callSt.setString(3, employee.getPhone());
            callSt.setDouble(4, employee.getSalary());
            callSt.setString(5, employee.getPosition());
            int rowsAffected = callSt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving employee", e);
        }
    }

    @Override
    public Employee findById(int id) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call findEmployeeById(?)}")) {
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("emp_id"));
                employee.setName(rs.getString("emp_name"));
                employee.setBirthday(rs.getDate("emp_dob").toLocalDate());
                employee.setPhone(rs.getString("emp_phone"));
                employee.setSalary(rs.getDouble("emp_salary"));
                employee.setPosition(rs.getString("emp_position"));
                return employee;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding employee by ID", e);
        }
    }

    @Override
    public boolean update(Employee employee) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call updateEmployeeById(?,?,?,?,?,?)}")) {
            callSt.setInt(1, employee.getId());
            callSt.setString(2, employee.getName());
            callSt.setDate(3, java.sql.Date.valueOf(employee.getBirthday()));
            callSt.setString(4, employee.getPhone());
            callSt.setDouble(5, employee.getSalary());
            callSt.setString(6, employee.getPosition());
            int rowsAffected = callSt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call deleteEmployeeById(?)}")) {
            callSt.setInt(1, id);
            int rowsAffected = callSt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }
}
