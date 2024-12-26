package com.api.Productos.CRUD.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.Productos.CRUD.models.entities.Producto;


public interface IProductoRespository extends JpaRepository<Producto, Long> {
    
}
