package com.ragheb.mvc.controller;

import com.ragheb.mvc.dto.RegistrationDto;
import com.ragheb.mvc.model.User;
import com.ragheb.mvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/register/save")
    public String register(@Valid
                           @ModelAttribute RegistrationDto user,
                           Model model,
                           BindingResult result
    ) {
        User existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail !=null && existingUserEmail.getEmail()!=null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        User existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername !=null && existingUsername.getUsername()!=null && !existingUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if (result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
