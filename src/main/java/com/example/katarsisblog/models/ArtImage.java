package com.example.katarsisblog.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ArtImage {
    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Column(unique = true)
    String name;

    String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "url",referencedColumnName = "url")
    @PrimaryKeyJoinColumn
    private Image image;
}
