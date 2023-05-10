package com.portfolio.Facuda.repository;

import com.portfolio.Facuda.entinty.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> { 
    
}