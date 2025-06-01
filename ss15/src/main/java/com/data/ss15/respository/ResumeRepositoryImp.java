package com.data.ss15.respository;

import com.data.ss15.model.Resume;
import com.data.ss15.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResumeRepositoryImp  implements ResumeRepository {
    @Override
    public void addResume(Resume resume) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call insert_resume(?, ?, ?, ?, ?, ?)}");
            callSt.setString(1, resume.getFullName());
            callSt.setString(2, resume.getEmail());
            callSt.setString(3, resume.getPhoneNumber());
            callSt.setString(4, resume.getEducation());
            callSt.setString(5, resume.getExperience());
            callSt.setString(6, resume.getSkills());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public List<Resume> getAllResumes() {
        List<Resume> list = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            callSt = conn.prepareCall("{call get_all_resumes()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Resume r = new Resume();
                r.setId(rs.getLong("id"));
                r.setFullName(rs.getString("full_name"));
                r.setEmail(rs.getString("email"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setEducation(rs.getString("education"));
                r.setExperience(rs.getString("experience"));
                r.setSkills(rs.getString("skills"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteResume(Long id) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call delete_resume(?)}");
            callSt.setLong(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public Resume findById(Long id) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Resume r = null;
        ResultSet rs = null;
        try {
            callSt = conn.prepareCall("{call find_by_id(?)}");
            callSt.setLong(1, id);
            rs = callSt.executeQuery();

            if (rs.next()) {
                r = new Resume();
                r.setId(rs.getLong("id"));
                r.setFullName(rs.getString("full_name"));
                r.setEmail(rs.getString("email"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setEducation(rs.getString("education"));
                r.setExperience(rs.getString("experience"));
                r.setSkills(rs.getString("skills"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public void updateResume(Resume resume) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call edit_resume(?,?, ?, ?, ?, ?, ?)}");
            callSt.setLong(1, resume.getId());
            callSt.setString(2, resume.getFullName());
            callSt.setString(3, resume.getEmail());
            callSt.setString(4, resume.getPhoneNumber());
            callSt.setString(5, resume.getEducation());
            callSt.setString(6, resume.getExperience());
            callSt.setString(7, resume.getSkills());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}
