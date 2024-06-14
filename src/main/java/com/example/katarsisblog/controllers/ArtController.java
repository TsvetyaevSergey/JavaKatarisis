package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.ArtImage;
import com.example.katarsisblog.models.Image;
import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.ArtImageRepository;
import com.example.katarsisblog.repo.FavouritesRepository;
import com.example.katarsisblog.repo.ImageRepository;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ArtController {
    @Autowired
    private ArtImageRepository artImageRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavouritesRepository favouritesRepository;

    @GetMapping("/art")
    public String artMain(Model model) {
        Set<ArtImage> favourites = Set.of();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
            String userName = authentication.getName();
            UserDTO user = userRepository.findByName(userName).orElseThrow();
            favourites = user.getFavourites().getArtImages();
        }
        Iterable<ArtImage> artImages = artImageRepository.findAll();
        model.addAttribute("artImages",artImages);
        model.addAttribute("favourites",favourites);
        return "art/art-main";
    }

    @GetMapping("/art/search")
    public String searchArt(@RequestParam String query, Model model) {
        Set<ArtImage> favourites = Set.of();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
            String userName = authentication.getName();
            UserDTO user = userRepository.findByName(userName).orElseThrow();
            favourites = user.getFavourites().getArtImages();
        }
        List<ArtImage> artImages = artImageRepository.findByNameStartingWith(query);
        model.addAttribute("artImages", artImages);
        model.addAttribute("favourites", favourites);
        return "art/art-main";
    }

    @GetMapping("/art/add")
    public String artAddPage(Model model) {
        return "art/art-add";
    }

    @PostMapping("/art/add")
    public String artAdd(@RequestParam String name, @RequestParam String anons,
                         @RequestParam String description, @RequestParam String url,
                         Model model) {
        ArtImage artImage = new ArtImage(name, description, anons, new Image(url));
        artImageRepository.save(artImage);
        return "redirect:/art";
    }

    @GetMapping("/art/{id}")
    public String artDetails(@PathVariable(value = "id") long id, Model model) {
        if (!artImageRepository.existsById(id)) {
            return "redirect:/art";
        }
        ArtImage artImage = artImageRepository.findById(id).orElseThrow();
        Image image = imageRepository.findById(artImage.getImage().getId()).orElseThrow();
        model.addAttribute("artImage", artImage);
        model.addAttribute("image", image);
        return "art/art-details";
    }

    @GetMapping("/art/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String artEdit(@PathVariable(value = "id") long id, Model model) {
        if (!artImageRepository.existsById(id)) {
            return "redirect:/art";
        }
        ArtImage artImage = artImageRepository.findById(id).orElseThrow();
        model.addAttribute("artImage", artImage);
        return "art/art-edit";
    }

    @PutMapping("/art/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String artostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String anons, @RequestParam String description, @RequestParam String url, Model model) {
        artImageRepository.deleteById(id);
        ArtImage artImage = new ArtImage();
        artImage.setId(id);
        artImage.setName(name);
        artImage.setAnons(anons);
        artImage.setDescription(description);
        artImage.setImage(new Image(url));
        artImageRepository.save(artImage);
        return "redirect:/art";
    }

    @DeleteMapping("/art/{id}/remove")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String artPostDelete(@PathVariable(value = "id") long id) {
        ArtImage artImage = artImageRepository.findById(id).orElseThrow();
        artImageRepository.delete(artImage);
        return "redirect:/art";
    }
}
