package com.data.hkt.repository.employee;

import com.data.hkt.model.EmployeeModel;

import java.util.List;

public interface Employee {
    List<EmployeeModel> getEmployee(int page, int size);
    boolean addDepartment(EmployeeModel emp);
    boolean updateEmployee(EmployeeModel emp);
    boolean deleteEmployee(int id);
    EmployeeModel getEmployeeById(int id);
    List<EmployeeModel> getEmployeeByName(String employeeName);
}
