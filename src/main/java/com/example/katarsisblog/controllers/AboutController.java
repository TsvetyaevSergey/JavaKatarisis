package com.example.katarsisblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String aboutMain(Model model) {
        model.addAttribute("title","Про меня");
        return "about";
    }
}
