package com.data.ontap.service;

import com.data.ontap.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll(int page, int pageSize);
    List<Course> searchByName(String name, int page, int pageSize);
    Course getById(Long id);
    boolean createCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(Long id);
    List<Course> findAllCous();
}
