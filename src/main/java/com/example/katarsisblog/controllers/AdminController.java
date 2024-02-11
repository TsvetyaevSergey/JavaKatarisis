package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private String adminMenu(Model model) {
        return "admin";
    }

    @PostMapping("/new-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newAdmin(@RequestParam String username, Model model) {
        Optional<UserDTO> user = userRepository.findByName(username);
        UserDTO result = user.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        result.setRoles("ROLE_ADMIN");
        userRepository.save(result);
        return "redirect:/";
    }
}
