package com.example.katarsisblog.services;

import com.example.katarsisblog.config.UserDTODetails;
import com.example.katarsisblog.models.UserDTO;
import com.example.katarsisblog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDTODetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDTO> user = repository.findByName(username);
        return user.map(UserDTODetails::new).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }
}
