/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.Facuda.controller;

import com.portfolio.Facuda.Dto.DtoHas;
import com.portfolio.Facuda.Security.Controller.Mensaje;
import com.portfolio.Facuda.entinty.has;
import com.portfolio.Facuda.service.Shas;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Soler-PC
 */
@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = {"http://localhost:4200","https://facudafrontend.web.app"})
public class CHas {

    @Autowired
    Shas shas;

    @GetMapping("/lista")
    public ResponseEntity<List<has>> list() {
        List<has> list = shas.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<has> getById(@PathVariable("id") int id) {
        if (!shas.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        has hAs = shas.getOne(id).get();
        return new ResponseEntity(hAs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shas.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        shas.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHas dtohas) {
        if (StringUtils.isBlank(dtohas.getNombre())) 
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (shas.existsByNombre(dtohas.getNombre()))
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
      

        has hAs = new has(dtohas.getNombre(), dtohas.getPorcentaje());
        shas.save(hAs);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHas dtohas) {
        if (!shas.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe esa ID"), HttpStatus.BAD_REQUEST);
        }
        if (shas.existsByNombre(dtohas.getNombre()) && shas.getByNombre(dtohas.getNombre()).get().getId() != id) 
            return new ResponseEntity(new Mensaje("Ya existe esa skill"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtohas.getNombre())) {
            return new ResponseEntity(new Mensaje("Porfavor, coloque una skill"), HttpStatus.BAD_REQUEST);
        }

        has hAs = shas.getOne(id).get();
        hAs.setNombre(dtohas.getNombre());
        hAs.setPorcentaje(dtohas.getPorcentaje());

        shas.save(hAs);
        return new ResponseEntity(new Mensaje("Se actualizó la skill"), HttpStatus.OK);
    }
}
