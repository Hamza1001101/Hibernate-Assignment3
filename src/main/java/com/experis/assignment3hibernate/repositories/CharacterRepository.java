package com.experis.assignment3hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.experis.assignment3hibernate.models.Character;

public interface CharacterRepository extends JpaRepository<Character,Long> {
}
