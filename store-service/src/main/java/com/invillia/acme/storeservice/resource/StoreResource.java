package com.invillia.acme.storeservice.resource;

import com.invillia.acme.storeservice.config.ResourceRepository;
import com.invillia.acme.storeservice.model.Store;
import com.invillia.acme.storeservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreResource extends ResourceRepository<Store> {

    private StoreRepository storeRepository;
    private Optional optList;

    @Autowired
    public StoreResource(JpaRepository<Store, Long> repository, StoreRepository storeRepository) {
        super(repository);
        this.storeRepository = storeRepository;
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name")String name){
        optList = storeRepository.findByNameContains(name);
        return ResponseEntity.ok(optList.isPresent() ? optList.get() : new ArrayList<>());
    }

    @GetMapping(value = "/address/{address}")
    public ResponseEntity<?> findByAddress(@PathVariable("address")String address){
        optList = storeRepository.findByAddressContains(address);
        return ResponseEntity.ok(optList.isPresent() ? optList.get() : new ArrayList<>());
    }
}
