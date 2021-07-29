package com.experis.assignment3hibernate.controllers;


import com.experis.assignment3hibernate.models.Character;
import com.experis.assignment3hibernate.models.Movie;
import com.experis.assignment3hibernate.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> data = characterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(data, status);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Optional<Character> characterData = characterRepository.findById(id);
        return characterData.map(character -> new ResponseEntity<>(character, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        Character add = characterRepository.save(character);
        HttpStatus status;
        status = HttpStatus.CREATED;
        // Return a location -> url to get the new resource
        return new ResponseEntity<>(add, status);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Character> updateAddress(@RequestBody Character character, @PathVariable Long id){
        HttpStatus status;
        Character retAdd = new Character();
        if(!Objects.equals(id, character.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(retAdd, status);
        }
        retAdd = characterRepository.save(character);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(retAdd, status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharecter(@PathVariable("id") long id) {
        try {
            characterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
