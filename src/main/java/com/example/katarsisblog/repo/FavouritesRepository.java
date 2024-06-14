package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.ArtImage;
import com.example.katarsisblog.models.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
}
