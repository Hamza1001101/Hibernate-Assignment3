package com.experis.assignment3hibernate.controllers;


import com.experis.assignment3hibernate.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")


public class FranchiseController {

    @Autowired
    private FranchiseController franchiseController;
}
