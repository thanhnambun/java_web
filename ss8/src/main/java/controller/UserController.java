package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
public class UserController {
    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User( "Nguyễn Văn A", "a@gmail.com", "0123456789"));
        userList.add(new User( "Trần Thị B", "b@gmail.com", "0987654321"));
        userList.add(new User("Lê Văn C", "c@gmail.com", "0911223344"));

        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }
}
