package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.Exposition;
import com.example.katarsisblog.models.Image;
import com.example.katarsisblog.repo.ExpositionRepository;
import com.example.katarsisblog.repo.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ExpositionController {
    @Autowired
    private ExpositionRepository expositionRepository;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/exposition")
    public String expositionMain(Model model) {
        Iterable<Exposition> expositions = expositionRepository.findAll();
        model.addAttribute("expositions", expositions);
        return "exposition/exposition-main";
    }

    @GetMapping("/exposition/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String blogAdd(Model model) {
        return "exposition/exposition-add";
    }

    @PostMapping("/exposition/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String expositionPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String urls, Model model) {
        List<Image> images = new ArrayList<>();
        ArrayList<String> splited_urls =  new ArrayList<>(Arrays.asList(urls.split("\n")));
        List<Image> imageList = imageRepository.findAll();
        ArrayList<String> imageListUrl = new ArrayList<>();
        for (Image cur : imageList) {
            imageListUrl.add(cur.getUrl());
        }
        for (String url : splited_urls) {
            Image image;
            if (!imageListUrl.contains(url))
            {
                image = new Image(url);
            } else
            {
                image = imageRepository.findByUrl(url);
            }
            images.add(image);
        }
        Exposition exposition = new Exposition(title, anons, full_text, images);
        expositionRepository.save(exposition);
        return "redirect:/exposition";
    }

    @GetMapping("/exposition/{id}")
    public String expositionDetails(@PathVariable(value = "id") long id, Model model) {
        if (!expositionRepository.existsById(id)) {
            return "redirect:/exposition";
        }
        Exposition exposition = expositionRepository.findById(id).orElseThrow();
        ArrayList<Image> images = new ArrayList<>(exposition.getImageList());
        model.addAttribute("images",images);
        model.addAttribute("exposition", exposition);
        return "exposition/exposition-details";
    }

    @GetMapping("/exposition/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String expositionEdit(@PathVariable(value = "id") long id, Model model) {
        if (!expositionRepository.existsById(id)) {
            return "redirect:/exposition";
        }
        Exposition exposition = expositionRepository.findById(id).orElseThrow();
        ArrayList<Image> images = new ArrayList<>(exposition.getImageList());
        StringBuilder sb = new StringBuilder();
        for (Image image : images) {
            sb.append(image.getUrl()).append("\n");
        }
        model.addAttribute("images",sb.toString());
        model.addAttribute("exposition", exposition);
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
