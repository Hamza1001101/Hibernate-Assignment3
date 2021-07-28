package com.experis.assignment3hibernate.controllers;


import com.experis.assignment3hibernate.models.Character;
import com.experis.assignment3hibernate.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



}
