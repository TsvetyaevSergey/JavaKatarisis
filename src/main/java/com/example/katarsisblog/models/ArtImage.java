package com.example.katarsisblog.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Transactional
@Data
@Table(name = "art_image")
public class ArtImage {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String anons;
    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "url", referencedColumnName = "url")
    private Image image;

    public ArtImage(String name, String description, String anons, Image image) {
        this.name = name;
        this.description = description;
        this.anons = anons;
        this.image = image;
    }

    public ArtImage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
