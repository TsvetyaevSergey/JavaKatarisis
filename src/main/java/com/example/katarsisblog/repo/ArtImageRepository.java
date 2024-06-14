package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.ArtImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtImageRepository extends JpaRepository<ArtImage,Long> {
    List<ArtImage> findByNameStartingWith(String prefix);
}
