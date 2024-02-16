package com.example.katarsisblog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.w3c.dom.Text;

import java.util.List;

@Entity
@Table(name = "exposition")
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String title,anons;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String full_text;
    private int views;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exp",referencedColumnName = "id")
    private List<Image> imageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Exposition() {
    }

    public Exposition(String title, String anons, String full_text, List<Image> imageList) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.imageList = imageList;
        this.views = 0;
    }
}
