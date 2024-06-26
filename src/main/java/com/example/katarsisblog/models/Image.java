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
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    private Long id;
    @Column(unique = true,columnDefinition = "TEXT")
    @NotEmpty
    @NotNull
    private String url;



    public Image(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
