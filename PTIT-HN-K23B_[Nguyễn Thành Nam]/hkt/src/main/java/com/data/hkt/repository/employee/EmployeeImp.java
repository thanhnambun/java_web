package com.data.hkt.repository.employee;

import com.data.hkt.model.DepartmentModel;
import com.data.hkt.model.EmployeeModel;
import com.data.hkt.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImp implements Employee{
    @Override
    public List<EmployeeModel> getEmployee(int page, int size) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<EmployeeModel> employeeModelList = null;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call find_all_employee(?,?)}");
            cstmt.setInt(1, page);
            cstmt.setInt(2, size);
            ResultSet rs = cstmt.executeQuery();
            employeeModelList = new ArrayList<>();
            while (rs.next()) {
                EmployeeModel emp = new EmployeeModel();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setFullName(rs.getString("full_name"));
                emp.setStatus(rs.getBoolean("status"));
                emp.setEmail(rs.getString("email"));
                emp.setAvatarUrl(rs.getString("avatar_url"));
                emp.setPhoneNumber(rs.getString("phone_number"));
                emp.setCreated_at(rs.getDate("created_at"));
                employeeModelList.add(emp);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return employeeModelList;
    }

    @Override
    public boolean addDepartment(EmployeeModel emp) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call add_emp(?,?,?,?,?,?)}");
            cstmt.setString(1, emp.getFullName());
            cstmt.setString(2, emp.getEmail());
            cstmt.setString(3, emp.getPhoneNumber());
            cstmt.setString(4, emp.getAvatarUrl());
            cstmt.setBoolean(5, emp.getStatus());
            cstmt.setInt(6,emp.getDepartmentId());
            cstmt.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEmployee(EmployeeModel emp) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call update_emp(?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, emp.getEmployeeId());
            cstmt.setString(2, emp.getFullName());
            cstmt.setString(3, emp.getEmail());
            cstmt.setString(4, emp.getPhoneNumber());
            cstmt.setString(5, emp.getAvatarUrl());
            cstmt.setBoolean(6, emp.getStatus());
            cstmt.setDate(7,emp.getCreated_at());
            cstmt.setInt(8,emp.getDepartmentId());
            cstmt.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteEmployee(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call update_emp(?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, id);
            cstmt.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public EmployeeModel getEmployeeById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        EmployeeModel emp = new EmployeeModel();
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call find_emp_by_id(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setFullName(rs.getString("full_name"));
                emp.setStatus(rs.getBoolean("status"));
                emp.setEmail(rs.getString("email"));
                emp.setAvatarUrl(rs.getString("avatar_url"));
                emp.setPhoneNumber(rs.getString("phone_number"));
                emp.setCreated_at(rs.getDate("created_at"));
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return emp;
    }

    @Override
    public List<EmployeeModel> getEmployeeByName(String employeeName) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<EmployeeModel> employeeModelList = null;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call search_emp_by_name(?)}");
            cstmt.setString(1, employeeName);
            ResultSet rs = cstmt.executeQuery();
            employeeModelList = new ArrayList<>();
            while (rs.next()) {
                EmployeeModel emp = new EmployeeModel();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setFullName(rs.getString("full_name"));
                emp.setStatus(rs.getBoolean("status"));
                emp.setEmail(rs.getString("email"));
                emp.setAvatarUrl(rs.getString("avatar_url"));
                emp.setPhoneNumber(rs.getString("phone_number"));
                emp.setCreated_at(rs.getDate("created_at"));
                employeeModelList.add(emp);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return employeeModelList;
    }
}
