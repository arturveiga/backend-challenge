package com.invillia.acme.storeservice.config;


import com.invillia.acme.storeservice.model.DefaultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;

@RestController
public abstract class ResourceRepository<E extends DefaultEntity> implements Serializable {
    protected JpaRepository<E,Long> repository;

    public ResourceRepository(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody E e){
        return ResponseEntity.ok(repository.save(e));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<E> optional = repository.findById(id);
        return optional.isPresent() ? ResponseEntity.ok(optional.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody E e){
        try {
            if(e.getId()==null || e.getId()==0){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            return ResponseEntity.ok(repository.save(e));
        } catch (Exception e1) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
