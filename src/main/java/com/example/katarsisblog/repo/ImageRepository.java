package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
