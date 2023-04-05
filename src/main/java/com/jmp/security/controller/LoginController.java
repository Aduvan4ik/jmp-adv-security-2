package com.jmp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(ModelMap model, @RequestParam Optional<String> error) {
        error.ifPresent(err -> model.addAttribute("error", err));
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccessPage() {
        return "logout";
    }


}
