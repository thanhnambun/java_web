package Controller;

import model.Employee;
import service.EmployeeService;
import service.EmployeeServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
    private final EmployeeService employeeService;

    public EmployeeController() {
        this.employeeService = new EmployeeServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            List<Employee> employees = employeeService.findAll();
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("/views/B4/employeeList.jsp").forward(req, resp);
        } else if (action.equals("search")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee employee = employeeService.findById(id);
            req.setAttribute("employees", employee != null ? List.of(employee) : List.of());
            req.getRequestDispatcher("/views/B4/employeeList.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee employee = employeeService.findById(id);
            if (employee != null) {
                req.setAttribute("employee", employee);
                req.getRequestDispatcher("/views/B4/updateEmployee.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/employee");
            }
        } else if (action.equals("add")) {
            req.getRequestDispatcher("/views/B4/addEmployee.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            Employee employee = new Employee();
            employee.setName(req.getParameter("name"));
            employee.setBirthday(LocalDate.parse(req.getParameter("birthday")));
            employee.setPhone(req.getParameter("phone"));
            employee.setSalary(Double.parseDouble(req.getParameter("salary")));
            employee.setPosition(req.getParameter("position"));
            employeeService.save(employee);
        } else if (action.equals("update")) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(req.getParameter("id")));
            employee.setName(req.getParameter("name"));
            employee.setBirthday(LocalDate.parse(req.getParameter("birthday")));
            employee.setPhone(req.getParameter("phone"));
            employee.setSalary(Double.parseDouble(req.getParameter("salary")));
            employee.setPosition(req.getParameter("position"));
            employeeService.update(employee);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            employeeService.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/employee");
    }
}