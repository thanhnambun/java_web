package com.example.bt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Bt8", value = "/bt8")
public class Bt8 extends HttpServlet {
    private List<Task> taskList;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        List<Task> taskList = (List<Task>) context.getAttribute("taskList");

        if (taskList == null) {
            taskList = new ArrayList<>();
            context.setAttribute("taskList", taskList);
        }

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String taskName = request.getParameter("taskName");
            if (taskName != null && !taskName.trim().isEmpty()) {
                taskList.add(new Task(taskName));
            }
        } else if ("toggle".equals(action)) {
            int index = Integer.parseInt(request.getParameter("index"));
            Task task = taskList.get(index);
            task.toggleCompleted();
        }

        response.sendRedirect("bt8.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        List<Task> taskList = (List<Task>) context.getAttribute("taskList");

        if (taskList == null) {
            taskList = new ArrayList<>();
            context.setAttribute("taskList", taskList);
        }

        request.setAttribute("taskList", taskList);
        request.getRequestDispatcher("bt8.jsp").forward(request, response);
    }
}