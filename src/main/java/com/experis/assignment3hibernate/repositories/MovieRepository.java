package com.experis.assignment3hibernate.repositories;

import com.experis.assignment3hibernate.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
