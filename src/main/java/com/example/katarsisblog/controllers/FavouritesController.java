package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.ArtImage;
import com.example.katarsisblog.models.Favourites;
import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.ArtImageRepository;
import com.example.katarsisblog.repo.FavouritesRepository;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
import java.util.Optional;

@Controller
public class FavouritesController {
    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private ArtImageRepository artImageRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/favourites/add/{artImageId}")
    public String addArtImageToFavourites(@PathVariable Long artImageId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDTO user = userRepository.findByName(userName).orElseThrow();
        Favourites favourites = user.getFavourites();
        ArtImage artImage = artImageRepository.findById(artImageId).orElseThrow();
        favourites.getArtImages().add(artImage);
        favouritesRepository.save(favourites);
        model.addAttribute(favourites.getArtImages());
        return "art/art-main";
    }
    @DeleteMapping("/favourites/delete/{artImageId}")
    public String deleteArtImagefromFavourites(@PathVariable Long artImageId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDTO user = userRepository.findByName(userName).orElseThrow();
        Favourites favourites = user.getFavourites();        ArtImage artImage = artImageRepository.findById(artImageId).orElseThrow();
        favourites.getArtImages().remove(artImage);
        favouritesRepository.save(favourites);
        Iterable<ArtImage> artImages = artImageRepository.findAll();
        model.addAttribute("artImages",artImages);
        model.addAttribute("favourites",favourites);
        return "art/art-main";
    }
}
