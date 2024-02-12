package com.example.katarsisblog.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "art_image")
public class ArtImage {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    @NotEmpty
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "url",referencedColumnName = "url")
    private Image image;
}
