package com.portfolio.Facuda.service;

import com.portfolio.Facuda.entinty.has;
import com.portfolio.Facuda.repository.Rhas;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class Shas {
    @Autowired
    Rhas rhas;
 
    
    public List<has> list(){
        return rhas.findAll();
    }
    
    public Optional<has> getOne(int id){
        return rhas.findById(id);
    }
    
    public Optional <has> getByNombre(String nombre){
        return rhas.findByNombre(nombre);
    }
    
    public void save(has skill){
        rhas.save(skill);
    }
    
    public void delete(int id){
        rhas.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhas.existsById(id);
    }
    
    public boolean existsByNombre (String nombre){
        return rhas.existsByNombre(nombre);
    }
}
