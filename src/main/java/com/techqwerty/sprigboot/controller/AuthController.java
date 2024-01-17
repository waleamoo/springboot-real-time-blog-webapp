package com.techqwerty.sprigboot.controller;

import org.hibernate.boot.jaxb.spi.Binding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.techqwerty.sprigboot.dto.RegistrationDto;
import com.techqwerty.sprigboot.entity.User;
import com.techqwerty.sprigboot.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    
    private UserService userService;

    public AuthController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already user with the same email");
            
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.SaveUser(user);
        return "redirect:/register?success";
    }
    

}
