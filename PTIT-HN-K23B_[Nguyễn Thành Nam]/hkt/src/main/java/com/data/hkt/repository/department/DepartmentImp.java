package com.data.hkt.repository.department;

import com.data.hkt.model.DepartmentModel;
import com.data.hkt.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DepartmentImp implements DepartmentInt {
    @Override
    public List<DepartmentModel> getDepartments(int page, int size) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<DepartmentModel> departments = null;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call find_all_department(?,?)}");
            cstmt.setInt(1, page);
            cstmt.setInt(2, size);
            ResultSet rs = cstmt.executeQuery();
            departments = new ArrayList<>();
            while (rs.next()) {
                DepartmentModel dpt = new DepartmentModel();
                dpt.setDepartmentId(rs.getInt("department_id"));
                dpt.setDepartmentName(rs.getString("department_name"));
                dpt.setDescription(rs.getString("description"));
                dpt.setStatus(rs.getBoolean("status"));
                departments.add(dpt);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return departments;
    }

    @Override
    public boolean addDepartment(DepartmentModel department) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call add_department(?,?)}");
            cstmt.setString(1, department.getDepartmentName());
            cstmt.setString(2, department.getDescription());
            cstmt.execute();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateDepartment(DepartmentModel department) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call update_department(?,?,?,?)}");
            cstmt.setInt(1, department.getDepartmentId());
            cstmt.setString(2, department.getDepartmentName());
            cstmt.setString(3, department.getDescription());
            cstmt.setBoolean(4, department.getStatus());
            cstmt.execute();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteDepartment(int id) {

        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call delete_department(?,?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.execute();
            result = true;
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return result;
    }

    @Override
    public DepartmentModel getDepartmentById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        DepartmentModel dpt = null;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call find_department_by_id(?,?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            dpt = new DepartmentModel();
            if (rs.next()) {
                dpt.setDepartmentId(rs.getInt("department_id"));
                dpt.setDepartmentName(rs.getString("department_name"));
                dpt.setDescription(rs.getString("description"));
                dpt.setStatus(rs.getBoolean("status"));
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return dpt;
    }

    @Override
    public List<DepartmentModel> getDepartmentByName(String departmentName) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<DepartmentModel> departments = null;
        try {
            conn = ConnectionDB.getConnection();
            cstmt = conn.prepareCall("{call search_department_by_name(?,?)}");
            cstmt.setString(1, departmentName);
            ResultSet rs = cstmt.executeQuery();
            departments = new ArrayList<>();
            while (rs.next()) {
                DepartmentModel dpt = new DepartmentModel();
                dpt.setDepartmentId(rs.getInt("department_id"));
                dpt.setDepartmentName(rs.getString("department_name"));
                dpt.setDescription(rs.getString("description"));
                dpt.setStatus(rs.getBoolean("status"));
                departments.add(dpt);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return departments;
    }


}
