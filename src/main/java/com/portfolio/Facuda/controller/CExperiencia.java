/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.Facuda.controller;

import com.portfolio.Facuda.Dto.DtoExperiencia;
import com.portfolio.Facuda.Security.Controller.Mensaje;
import com.portfolio.Facuda.entinty.Experiencia;
import com.portfolio.Facuda.service.SExperiencia;
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

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = "https://facudafrontend.web.app")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity <List<Experiencia>> list(){
        List <Experiencia> list = sExperiencia.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperiencia.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sExperiencia.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
    
    @PostMapping ("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("Porfavor, coloque un nombre"), HttpStatus.BAD_REQUEST);
        if (sExperiencia.existByNombreE(dtoexp.getNombreE()))
             return new ResponseEntity(new Mensaje ("Ya existe esa experiencia"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity (new Mensaje ("experiencia agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping ("/update/{id}")
    public ResponseEntity <?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoexp){
        //validamos si existe el ID
        if(!sExperiencia.existsById(id))
            return new ResponseEntity (new Mensaje ("no existe esa ID"), HttpStatus.BAD_REQUEST);
        //compara nombre de experiencia
        if (sExperiencia.existByNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity (new Mensaje("Ya existe esa experiencia"), HttpStatus.BAD_REQUEST);
        //no puede estar vacio
        if (StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity (new Mensaje("Porfavor, coloque un nombre"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        sExperiencia.save(experiencia);
        return new ResponseEntity (new Mensaje("Se actualizó la experiencia"), HttpStatus.OK);
    }
   
}
