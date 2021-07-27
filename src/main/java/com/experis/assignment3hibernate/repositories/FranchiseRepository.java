package com.experis.assignment3hibernate.repositories;

import com.experis.assignment3hibernate.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {
}
