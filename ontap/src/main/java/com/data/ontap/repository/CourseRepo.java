package com.data.ontap.repository;

import com.data.ontap.model.Course;

import java.util.List;

public interface CourseRepo {
    List<Course> findAll(int page, int pageSize);
    List<Course> findByName(String name,int page, int pageSize);
    Course findById(Long id);
    boolean save(Course course);
    boolean delete(Long id);
    boolean update(Course course);
    List<Course> findAllCous();
}
