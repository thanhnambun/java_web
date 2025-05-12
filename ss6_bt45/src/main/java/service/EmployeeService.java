package service;

import model.Employee;

import java.util.List;

public interface EmployeeService {
        List<Employee> findAll();
        boolean save(Employee employee);

        Employee findById(int id);

        boolean update(Employee employee);

        boolean delete(int id);
    }
