/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.Facuda.repository;

import com.portfolio.Facuda.entinty.has;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Soler-PC
 */
public interface Rhas extends JpaRepository<has, Integer> {
    Optional<has> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
