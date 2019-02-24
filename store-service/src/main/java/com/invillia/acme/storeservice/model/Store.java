package com.invillia.acme.storeservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "stores")
public class Store extends DefaultEntity {

    @NotEmpty(message = "Name is Required")
    private String name;
    @NotEmpty(message = "Address is Required")
    private String address;

}

