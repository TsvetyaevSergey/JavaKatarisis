package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.Exposition;
import com.example.katarsisblog.repo.ExpositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ExpositionController {
    @Autowired
    private ExpositionRepository expositionRepository;

    @GetMapping("/blog")
    public String expositionMain(Model model) {
        Iterable<Exposition> expositions = expositionRepository.findAll();
        model.addAttribute("expositions", expositions);
        return "blog/blog-main";
    }

    @GetMapping("/exposition/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String blogAdd(Model model) {
        return "exposition/exposition-add";
    }

    @PostMapping("/exposition/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String expositionPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Exposition exposition = new Exposition(title, anons, full_text);
        expositionRepository.save(exposition);
        return "redirect:/exposition";
    }

    @GetMapping("/exposition/{id}")
    public String expositionDetails(@PathVariable(value = "id") long id, Model model) {
        if (!expositionRepository.existsById(id)) {
            return "redirect:/exposition";
        }
        Optional<Exposition> exposition = expositionRepository.findById(id);
        ArrayList<Exposition> result = new ArrayList<>();
        exposition.ifPresent(result::add);
        model.addAttribute("exposition", result);
        return "exposition/exposition-details";
    }

    @GetMapping("/exposition/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String expositionEdit(@PathVariable(value = "id") long id, Model model) {
        if (!expositionRepository.existsById(id)) {
            return "redirect:/exposition";
        }
        Optional<Exposition> exposition = expositionRepository.findById(id);
        ArrayList<Exposition> result = new ArrayList<>();
        exposition.ifPresent(result::add);
        model.addAttribute("exposition", result);
        return "exposition/exposition-edit";
    }

    @PutMapping("/exposition/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String expositionPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Exposition exposition = expositionRepository.findById(id).orElseThrow();
        exposition.setTitle(title);
        exposition.setAnons(anons);
        exposition.setFull_text(full_text);
        expositionRepository.save(exposition);
        return "redirect:/exposition";
    }

    @DeleteMapping("/exposition/{id}/remove")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String blogPostDelete(@PathVariable(value = "id") long id) {
        Exposition exposition = expositionRepository.findById(id).orElseThrow();
        expositionRepository.delete(exposition);
        return "redirect:/exposition";
    }

}
