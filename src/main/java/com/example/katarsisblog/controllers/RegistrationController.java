package com.example.katarsisblog.controllers;

import com.example.katarsisblog.exeption.UserAlreadyExistAuthenticationException;
import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/new-user")
    public String addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        try
        {
            UserDTO user = new UserDTO(username, passwordEncoder.encode(password), email);
            userRepository.save(user);
            return "redirect:/login";
        }
        catch (Exception ignored) {
            System.out.println(username+" Already Exist");
            return "redirect:/registration";
        }

    }

    @PostMapping("/new-admin")
    public String newAdmin(@RequestParam String username, Model model) {
        Optional<UserDTO> user = userRepository.findByName(username);
        UserDTO result = user.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        result.setRoles("ROLE_ADMIN");
        userRepository.save(result);
        return "redirect:/";
    }
}
