package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO,Long> {
    Optional<UserDTO> findByName(String username);
}
