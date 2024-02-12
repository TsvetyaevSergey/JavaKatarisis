package com.example.katarsisblog.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @OneToOne(mappedBy = "image")
    @PrimaryKeyJoinColumn
    private ArtImage artImage;

    @NotEmpty
    @Column(unique = true)
    private String url;
}
