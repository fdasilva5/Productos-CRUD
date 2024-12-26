package com.api.Productos.CRUD.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.api.Productos.CRUD.models.dtos.ProductoDTO;
import com.api.Productos.CRUD.models.entities.Producto;

public interface IProductoService {
    public List<ProductoDTO> getProductos();
    public Optional<ProductoDTO> getProductoById(Long id);
    public ProductoDTO saveProducto(Producto producto);
    public void deleteProducto(Long id);
    public ProductoDTO updateProducto(Producto producto);
    
}
