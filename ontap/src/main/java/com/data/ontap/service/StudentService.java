package com.data.ontap.service;

import com.data.ontap.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll(int page, int pageSize);
    List<Student> findByName(String name,int page, int pageSize);
    Student findById(String id);
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(String id);
    List<Student> findAllSt();
}
