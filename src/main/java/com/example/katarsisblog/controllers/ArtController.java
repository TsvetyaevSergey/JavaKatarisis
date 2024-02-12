package com.example.katarsisblog.controllers;

import com.example.katarsisblog.models.ArtImage;
import com.example.katarsisblog.repo.ArtImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ArtController {
    @Autowired
    private ArtImageRepository artImageRepository;
    @GetMapping("/art")
    public String artMain(Model model) {
        return "art";
    }

    @PostMapping("/addArtImage")
    public ResponseEntity<String> addArtImage(@RequestBody List<ArtImage> artImage){
        artImageRepository.saveAll(artImage);
        return ResponseEntity.ok("Data saved");
    }


}
