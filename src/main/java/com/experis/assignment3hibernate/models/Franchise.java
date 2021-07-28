package com.experis.assignment3hibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Franchises")
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "franchise_name")
    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "movies_id")
    List<Movie> movies;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
