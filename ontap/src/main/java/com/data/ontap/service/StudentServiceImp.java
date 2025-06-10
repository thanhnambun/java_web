package com.data.ontap.service;

import com.data.ontap.model.Student;
import com.data.ontap.repository.StudentRepo;
import com.data.ontap.repository.StudentRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Override
    public List<Student> findAll(int page, int pageSize) {
        return studentRepo.findAll(page, pageSize);
    }

    @Override
    public List<Student> findByName(String name, int page, int pageSize) {
        return studentRepo.findByName(name, page, pageSize);
    }

    @Override
    public Student findById(String id) {
        return studentRepo.findById(id);
    }

    @Override
    public boolean save(Student student) {
        if(student.getId() == null || student.getId().isEmpty()) {
            student.setId(generateStudentId());
        }
        return studentRepo.save(student);
    }


    @Override
    public boolean update(Student student) {
        return studentRepo.update(student);
    }

    @Override
    public boolean delete(String id) {
        return studentRepo.delete(id);
    }

    @Override
    public List<Student> findAllSt() {
        return studentRepo.findAllSt();
    }

    public String generateStudentId() {
        Student lastStudent = studentRepo.findTopByOrderByIdDesc();
        if (lastStudent == null) {
            return "SV001";
        }

        String lastId = lastStudent.getId();
        int lastNumber = Integer.parseInt(lastId.substring(2));
        int newNumber = lastNumber + 1;

        return String.format("SV%03d", newNumber);
    }


    @Autowired
    private StudentRepo studentRepo;

}
