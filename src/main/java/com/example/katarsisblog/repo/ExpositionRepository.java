package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.Exposition;
import com.example.katarsisblog.models.Image;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
}
