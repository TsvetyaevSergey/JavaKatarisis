package com.example.katarsisblog.controllers;

import com.example.katarsisblog.exeption.UserAlreadyExistAuthenticationException;
import com.example.katarsisblog.models.Favourites;
import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.FavouritesRepository;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private FavouritesRepository favouritesRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/new-user")
    public String addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String role, Model model) {
            UserDTO user = new UserDTO(username, passwordEncoder.encode(password), email, role, new Favourites());
            userRepository.save(user);
            Favourites favourites = favouritesRepository.findById(user.getFavourites().getId()).orElseThrow();
            favourites.setUserDTO(user);
            favouritesRepository.save(favourites);
            return "redirect:/login";

    }
}
