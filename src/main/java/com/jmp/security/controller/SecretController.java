package com.jmp.security.controller;

import com.jmp.security.entity.Secret;
import com.jmp.security.exception.LinkNotFoundException;
import com.jmp.security.service.impl.SecretService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @GetMapping("/")
    public String getSecretCreationPage() {
        return "secret-creation";
    }

    @PostMapping("/secret")
    public String createSecret(@ModelAttribute("secretValue") String secretValue,
                               Model model, HttpServletRequest request) {
        String link = secretService.saveNewSecret(secretValue);
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        model.addAttribute("link", baseUrl + "/secret/" + link);
        return "secret-link";
    }

    @GetMapping("secret/{link}")
    public String viewSecret(@PathVariable String link, Model model) {
        Secret secret = secretService.findAndDeleteByLink(link);
        model.addAttribute("secret", secret);
        return "secret-view";
    }

    @ExceptionHandler({LinkNotFoundException.class})
    public String getLinkNotFoundExceptionPage() {
        return "error/404";
    }
}
