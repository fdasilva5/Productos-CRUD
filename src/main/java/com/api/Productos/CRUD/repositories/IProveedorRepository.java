package com.api.Productos.CRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.Productos.CRUD.models.entities.Proveedor;

public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {
    
}
