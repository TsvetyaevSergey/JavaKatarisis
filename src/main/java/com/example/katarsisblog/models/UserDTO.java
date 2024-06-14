package com.example.katarsisblog.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String roles;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favourite_id", referencedColumnName = "id")
    private Favourites favourites;

    public Favourites getFavourites() {
        return favourites;
    }

    public void setFavourites(Favourites favourites) {
        this.favourites = favourites;
    }

    public UserDTO(String name, String password, String email, String roles,Favourites favourites ) {
        this.name = name;
        this.password = password;
        this.email = email;

        switch (roles) {
            case "admin":
                this.roles = "ROLE_ADMIN";
                break;
            case "user":
                this.roles = "ROLE_USER";
                break;
            default:
                throw new IllegalArgumentException("Неизвестная роль: " + roles);
        }
        this.favourites = favourites;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
