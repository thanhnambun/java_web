package service;
import DAO.EmployeeDAOImp;
import model.Employee;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    private final bt4.EmployeeDAO employeeDao;

    public EmployeeServiceImp() {
        employeeDao = new EmployeeDAOImp();
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public boolean save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public boolean delete(int id) {
        return employeeDao.delete(id);
    }
}
