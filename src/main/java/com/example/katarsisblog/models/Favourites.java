package com.example.katarsisblog.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "favourites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDTO userDTO;

    @ManyToMany
    @JoinTable(
            name = "favourites_art_image",
            joinColumns = @JoinColumn(name = "favourites_id"),
            inverseJoinColumns = @JoinColumn(name = "art_image_id")
    )
    private Set<ArtImage> artImages;

    public Set<ArtImage> getArtImages() {
        return artImages;
    }

    public void setArtImages(Set<ArtImage> artImages) {
        this.artImages = artImages;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Favourites() {
    }

    public Favourites(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
