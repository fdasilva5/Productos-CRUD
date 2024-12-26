package com.api.Productos.CRUD.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @OneToMany(mappedBy = "proveedor")
    @JsonManagedReference
    private List<Producto> productos;


}
