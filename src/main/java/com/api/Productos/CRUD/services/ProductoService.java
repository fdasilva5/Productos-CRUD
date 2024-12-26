package com.api.Productos.CRUD.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Productos.CRUD.models.dtos.ProductoDTO;
import com.api.Productos.CRUD.models.entities.Producto;
import com.api.Productos.CRUD.repositories.IProductoRespository;

@Service
public class ProductoService {
    
    @Autowired
    private IProductoRespository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductoDTO> getProductos() {

        // Obtener todos los productos
        List<Producto> productos = productoRepository.findAll();
        
        return productos.stream()
            .map(producto -> {
                ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
                return productoDTO;
            })
            .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductoById(Long id) {

        // Buscar un producto por su id
        Optional<Producto> producto = productoRepository.findById(id);

        return Optional.ofNullable(producto.map(p -> {
            ProductoDTO productoDTO = modelMapper.map(p, ProductoDTO.class);
            return productoDTO;
        }).orElse(null));
    }

    public ProductoDTO saveProducto(Producto producto) {

        // Me aseguro de que el id sea null para evitar sobrescribir productos existentes
        producto.setId(null);

        Producto crearProducto = productoRepository.save(producto);
        ProductoDTO productoDTO = modelMapper.map(crearProducto, ProductoDTO.class);
      
        return productoDTO;
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {

        // Buscar el producto existente por id
        Optional<Producto> existingProductoOpt = productoRepository.findById(id);
        if (existingProductoOpt.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }
    
        // Mapear el productoDTO a la entidad Producto
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        producto.setId(id); 
    
        // Guardar el producto actualizado en la base de datos
        Producto updatedProducto = productoRepository.save(producto);
        
        // Mapear la entidad Producto actualizada a ProductoDTO
        ProductoDTO updatedProductoDTO = modelMapper.map(updatedProducto, ProductoDTO.class);
        
        // Devolver el ProductoDTO actualizado
        return updatedProductoDTO;
    }
}