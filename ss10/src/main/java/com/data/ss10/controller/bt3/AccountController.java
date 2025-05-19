
package com.data.ss10.controller.bt3;
import com.data.ss10.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    private List<Account> accountList = new ArrayList<>();

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("account", new Account());
        return "/bt3/registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("account") Account account, Model model) {
        accountList.add(account);
        model.addAttribute("message", "Đăng ký thành công!");
        return "/bt3/success";
    }

    @GetMapping("/accounts")
    public String showAccounts(Model model) {
        model.addAttribute("accounts", accountList);
        return "/bt3/accountList";
    }
}
