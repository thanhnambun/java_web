package com.data.hkt.repository.department;

import com.data.hkt.model.DepartmentModel;

import java.util.List;

public interface DepartmentInt {
    List<DepartmentModel> getDepartments(int page, int size);
    boolean addDepartment(DepartmentModel department);
    boolean updateDepartment(DepartmentModel department);
    boolean deleteDepartment(int id);
    DepartmentModel getDepartmentById(int id);
    List<DepartmentModel> getDepartmentByName(String departmentName);
}
