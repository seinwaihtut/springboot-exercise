package com.springboot.exercise.controllers;

import com.springboot.exercise.models.User;
import com.springboot.exercise.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/user/register")
    public String register(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "user_input_form";
    }

    @PostMapping("/user/register")
    public String studentSave(@Valid @ModelAttribute User user, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "user_input_form";
        }

        User usr = userService.create(user);

        return "redirect:/user/login";
    }

//    @GetMapping("/user/login")
    @GetMapping("/login")
    public String login(Model model) {

        return "user_login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user_login";
    }

    @GetMapping("/")
    public String landingPage(Model model, Authentication authentication) {
        if (authentication != null) {
            return "index";
        }
        return "login";
    }
}
