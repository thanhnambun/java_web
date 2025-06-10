package com.data.ontap.repository;

import com.data.ontap.model.Student;

import java.util.List;

public interface StudentRepo {
    List<Student> findAll(int page, int pageSize);
    List<Student> findByName(String name,int page, int pageSize);
    Student findById(String id);
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(String id);
    Student findTopByOrderByIdDesc();
    List<Student> findAllSt();
}
