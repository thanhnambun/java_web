package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class bt1 {
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
