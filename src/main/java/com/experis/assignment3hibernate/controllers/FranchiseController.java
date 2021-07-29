package com.experis.assignment3hibernate.controllers;


import com.experis.assignment3hibernate.models.Character;
import com.experis.assignment3hibernate.models.Franchise;
import com.experis.assignment3hibernate.models.Movie;
import com.experis.assignment3hibernate.repositories.CharacterRepository;
import com.experis.assignment3hibernate.repositories.FranchiseRepository;
import com.experis.assignment3hibernate.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private MovieRepository movieRepository;


    //working
    @GetMapping
    public ResponseEntity<List<Franchise>> getAllFranchise() {
        List<Franchise> data = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }

    //working
    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise add = franchiseRepository.save(franchise);
        HttpStatus status;
        status = HttpStatus.CREATED;
        // Return a location -> url to get the new resource
        return new ResponseEntity<>(add, status);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getSpecificFranchise(@PathVariable Long id) {
        HttpStatus status;
        Franchise add = new Franchise();
        if (!franchiseRepository.existsById(id)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(add, status);
        }
        add = franchiseRepository.findById(id).get();
        status = HttpStatus.OK;
        return new ResponseEntity<>(add, status);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable("id") Long id, @RequestBody Franchise franchise) {
        Optional<Franchise> franchiseData = franchiseRepository.findById(id);
        if (franchiseData.isPresent()) {
            Franchise _franchise = franchiseData.get();
            _franchise.setName(franchise.getName());
            _franchise.setDescription(franchise.getDescription());

            return new ResponseEntity<>(franchiseRepository.save(_franchise), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //working
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFranchise(@PathVariable("id") long id) {
        try {
            franchiseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}/movies")
    public ResponseEntity<Franchise> updateMoviesInFranchise(@PathVariable Long id, @RequestBody List<Long> moviesId) {
        Franchise franchise;
        if (!franchiseRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        franchise = franchiseRepository.findById(id).get();
        List<Movie> movies = new ArrayList<>();
        for (Long movieId : moviesId)
            if (movieRepository.existsById(movieId))
                movies.add(movieRepository.findById(movieId).get());

        franchise.setMovies(movies);
        franchiseRepository.save(franchise);
        return new ResponseEntity<>(franchise, HttpStatus.OK);
    }

   @GetMapping("/getAllMoviesInFranchises/{id}")
    public ResponseEntity<List<Movie>> getAllMoviesInFranchises(@PathVariable Long id) {
        Franchise franchise;
        List<Movie> movies = new ArrayList<>();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            franchise = franchiseRepository.findById(id).get();
            movies = franchise.getMovies();
        }else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movies, status);
    }

    @GetMapping("{id}/getAllCharactersInFranchise/")
    public ResponseEntity<List<Character>> getAllCharactersInFranchise(@PathVariable Long id) {
        List<Movie> movies;

        HttpStatus status;

        if (!franchiseRepository.existsById(id)) {
            status= HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }

        movies = franchiseRepository.findById(id).get().getMovies();
        HashMap<Long, Character> characterHashMap = new HashMap<>();

        movies.stream().map(Movie::getCharacters)
                .forEach(list -> {
                    list.forEach(character -> {
                        characterHashMap.put(character.getId(), character);
                    });
                });
        List<Character> characters = new ArrayList<Character>((Collection<? extends Character>) characterHashMap);

        status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }

}