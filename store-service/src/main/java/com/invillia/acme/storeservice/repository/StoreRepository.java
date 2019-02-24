package com.invillia.acme.storeservice.repository;

import com.invillia.acme.storeservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<List<Store>> findByNameContains(String name);
    Optional<List<Store>> findByAddressContains(String address);
}
